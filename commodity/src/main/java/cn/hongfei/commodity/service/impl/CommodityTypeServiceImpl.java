package cn.hongfei.commodity.service.impl;

import cn.hongfei.commodity.entity.CommodityType;
import cn.hongfei.commodity.mapper.CommodityTypeMapper;
import cn.hongfei.commodity.service.CommodityTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CommodityTypeServiceImpl extends ServiceImpl<CommodityTypeMapper, CommodityType> implements CommodityTypeService {
}
