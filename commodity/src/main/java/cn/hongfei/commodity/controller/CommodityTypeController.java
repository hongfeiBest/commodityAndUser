package cn.hongfei.commodity.controller;

import cn.hongfei.commodity.entity.CommodityType;
import cn.hongfei.commodity.helper.CommodityTypeHelper;
import cn.hongfei.commodity.service.impl.CommodityTypeServiceImpl;
import cn.hongfei.common.response.entity.Response;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/commodityType")
public class CommodityTypeController {

    @Autowired
    private CommodityTypeServiceImpl commodityTypeService;

    @Autowired
    private CommodityTypeHelper commodityTypeHelper;

    @GetMapping("/list")
    public Response list(@RequestParam(required = false) Integer delFlag){
        return commodityTypeHelper.list(delFlag,commodityTypeService);
    }

    @PostMapping("/add")
    public Response add(@RequestBody CommodityType commodityType, HttpServletRequest request){
        Object userName = request.getSession().getAttribute("userName");
        if (ObjectUtils.isEmpty(userName)){
            return Response.failedAndMessage("请先登录~~~");
        }
        commodityType.setCreateUser(userName.toString());
        boolean save = commodityTypeService.save(commodityType);
        if (save){
            return Response.successAndData("新增商品类型成功~~~");
        }
        return Response.failedAndMessage("新增商品类型失败~~~");
    }

}
