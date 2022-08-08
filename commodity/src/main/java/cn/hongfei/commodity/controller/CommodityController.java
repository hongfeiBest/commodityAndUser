package cn.hongfei.commodity.controller;


import cn.hongfei.commodity.entity.Commodity;
import cn.hongfei.commodity.helper.CommodityHelper;
import cn.hongfei.commodity.service.BuyHistoryService;
import cn.hongfei.commodity.service.CommodityTypeService;
import cn.hongfei.commodity.service.impl.CommodityServiceImpl;
import cn.hongfei.common.response.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityServiceImpl commodityService;

    @Autowired
    private CommodityHelper commodityHelper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private BuyHistoryService buyHistoryService;

    @Autowired
    private CommodityTypeService commodityTypeService;

    /**
     * 查询商品列表(分页查询) current：当前页，size：记录数量
     * @param commodityTypeId  商品ID
     * @param name             商品名称
     * @return                 商品列表
     */
    @GetMapping("/list")
    public Response list(@RequestParam(required = false) String commodityTypeId, @RequestParam(required = false) String name,@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") Integer size){
        return commodityHelper.list(commodityTypeId,name,current,size,commodityService);
    }

    /**
     * 新增商品
     * @param commodity   Commodity实例
     * @param request     HTTPRequest
     * @return            success/failed
     */
    @PostMapping("/add")
    public Response add(@RequestBody Commodity commodity, HttpServletRequest request){
//        Object userName = request.getSession().getAttribute("userName");
//        if (ObjectUtils.isEmpty(userName)){
//            return Response.failedAndMessage("请先登录~~~");
//        }
//        commodity.setCreateUser(userName.toString());
        boolean save = commodityService.save(commodity);
        if (save){
            return Response.successAndData("新增商品:"+commodity.getName()+"成功~~~");
        }
        return Response.failedAndMessage("新增商品:"+commodity.getName()+"失败~~~");
    }

    /**
     * 购买商品
     * @param userId             用户ID
     * @param commodityId        商品ID
     * @param commodityCount     商品数量
     * @return                   success/failed
     */
    @GetMapping("/buy")
    @Transactional
    public Response buy(@RequestParam String userId,@RequestParam String commodityId,@RequestParam Integer commodityCount){
        return commodityHelper.buy(commodityService,buyHistoryService,commodityTypeService,redisTemplate,userId,commodityId,commodityCount);
    }

}
