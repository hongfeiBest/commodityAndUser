package cn.hongfei.commodity.service.impl;

import cn.hongfei.commodity.entity.Commodity;
import cn.hongfei.commodity.mapper.CommodityMapper;
import cn.hongfei.commodity.service.CommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService {
}
