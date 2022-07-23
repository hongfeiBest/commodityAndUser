package cn.hongfei.commodity.helper;

import cn.hongfei.commodity.entity.BuyHistory;
import cn.hongfei.commodity.entity.Commodity;
import cn.hongfei.commodity.entity.CommodityType;
import cn.hongfei.commodity.service.BuyHistoryService;
import cn.hongfei.commodity.service.CommodityService;
import cn.hongfei.commodity.service.CommodityTypeService;
import cn.hongfei.commodity.service.impl.CommodityServiceImpl;
import cn.hongfei.common.response.entity.Response;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Component
public class CommodityHelper {
    /**
     * 查询商品列表
     * @param commodityTypeId     商品ID
     * @param name                商品名称
     * @param commodityService    CommodityService
     * @return
     */
    public Response list(String commodityTypeId, String name, CommodityService commodityService) {
        QueryWrapper<Commodity> commodityQueryWrapper = new QueryWrapper<>();
        if (ObjectUtils.isNotEmpty(name)){
            commodityQueryWrapper.eq("name",name);
        }
        if (ObjectUtils.isNotEmpty(commodityTypeId)){
            commodityQueryWrapper.eq("commodity_type_id",commodityTypeId);
        }
        List<Commodity> list = commodityService.list(commodityQueryWrapper);
        return Response.successAndData(list);
    }

    /**
     * 购买商品
     *
     * @param commodityService   CommodityService；
     * @param redisTemplate      StringRedisTemplate
     * @param userId             用户ID
     * @param commodityId        商品ID
     * @param commodityCount     商品数量
     * @return                   购买反馈消息
     */
    public Response buy(CommodityServiceImpl commodityService, BuyHistoryService buyHistoryService, CommodityTypeService commodityTypeService, StringRedisTemplate redisTemplate, String userId, String commodityId, Integer commodityCount) {

        String redisKey = "redisKey";
        String redisValue = UUID.randomUUID().toString();
        //设置锁过期时间，单位秒钟
        int timeOut = 10;

        //加锁操作
        //当且仅当key不存在时，set一个key字符串；若key存在，则什么都不做。
        Boolean success = redisTemplate.opsForValue().setIfAbsent(redisKey, redisValue, Duration.ofSeconds(timeOut));

        //获取商品信息
        Commodity commodity = null ;
        //加锁成功之后再做其他操作
        if (success) {
            try {
                System.out.println("~~~~~~~~~~购买商品redis已加锁~~~~~~~~~~");
                commodity=commodityService.getById(commodityId);
                //购买商品后需要减少商品库存
                commodity.setStock(commodity.getStock() - commodityCount);
                //更新库存后更新商品
                commodityService.updateById(commodity);

                //拼接消息：规则：userID+","+score+","+money+","+count
                String message = userId + "," + commodity.getMoney() / 10 + "," + commodity.getMoney() + "," + commodityCount;

                //查询商品类型
                CommodityType commodityType = commodityTypeService.getById(commodity.getCommodityTypeId());

                //新增购买记录
                BuyHistory buyHistory = new BuyHistory();
                buyHistory.setUserId(userId);
                buyHistory.setCommodityType(commodityType.getCommodityTypeName());
                buyHistory.setCommodityName(commodity.getName());
                buyHistory.setStandard(commodity.getStandard());
                buyHistory.setCount(commodityCount);
                buyHistory.setScore(commodity.getMoney() / 10);
                buyHistory.setMoney(commodity.getMoney());
                //新增商品购买记录
                buyHistoryService.save(buyHistory);

                //向redis通道中发送消息，通知用户加积分
                //通道名称：user_score
                redisTemplate.convertAndSend("user_score", message);
                System.out.println("~~~~~已通知用户："+userId+"--添加积分~~~~~");

                //做等待测试用
                for (int i = 1; i <= 5; i++) {
                    Thread.sleep(1000);
                    System.out.println("~~~~~购买倒计时~~锁："+i+"秒钟~~~~~");
                }

            }catch (Exception e){
                return Response.failedAndMessage("系统异常，请稍后重试~~~");
            }finally {
                //redis 解锁操作
                boolean success2 = releaseLock(redisKey, redisValue, redisTemplate);
                if (success2){
                    System.out.println("~~~~~~~~~~redis已解锁~~~~~~~~~~");
                }
            }
        }else {
            return Response.failedAndMessage("购买人数过多~`~请稍后重试~~~");
        }

        return Response.successAndData("购买"+commodity.getName()+"成功~~~");
    }

    /**
     *redis解锁操作
     *
     * @param key           redisKey
     * @param value         redisValue
     * @param redisTemplate StringRedisTemplate
     * @return              true/false
     */
    private boolean releaseLock(String key, String value,StringRedisTemplate redisTemplate){
        String srcValue = redisTemplate.opsForValue().get(key);
        if (ObjectUtils.isEmpty(srcValue)){
            return true;
        }
        else if (srcValue.equals(value)){
            redisTemplate.delete(key);
            return true;
        }
        return false;
    }
}
