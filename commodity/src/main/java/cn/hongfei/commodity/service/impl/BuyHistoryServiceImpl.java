package cn.hongfei.commodity.service.impl;

import cn.hongfei.commodity.entity.BuyHistory;
import cn.hongfei.commodity.mapper.BuyHistoryMapper;
import cn.hongfei.commodity.service.BuyHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BuyHistoryServiceImpl extends ServiceImpl<BuyHistoryMapper, BuyHistory> implements BuyHistoryService {
}
