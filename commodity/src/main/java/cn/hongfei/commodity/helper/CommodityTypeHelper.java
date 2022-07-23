package cn.hongfei.commodity.helper;

import cn.hongfei.commodity.entity.CommodityType;
import cn.hongfei.commodity.service.impl.CommodityTypeServiceImpl;
import cn.hongfei.common.response.entity.Response;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommodityTypeHelper {
    public Response list(Integer delFlag , CommodityTypeServiceImpl commodityTypeService) {
        QueryWrapper<CommodityType> commodityTypeQueryWrapper = new QueryWrapper<>();
        if (ObjectUtils.isNotEmpty(delFlag)){
            commodityTypeQueryWrapper.eq("del_flag",delFlag);
        }
        List<CommodityType> list = commodityTypeService.list(commodityTypeQueryWrapper);
        return Response.successAndData(list);
    }
}
