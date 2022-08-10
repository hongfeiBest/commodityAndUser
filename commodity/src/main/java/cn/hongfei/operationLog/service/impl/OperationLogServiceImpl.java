package cn.hongfei.operationLog.service.impl;

import cn.hongfei.operationLog.entity.OperationLog;
import cn.hongfei.operationLog.mapper.OperationLogMapper;
import cn.hongfei.operationLog.service.OperationLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author : hongfeiBest
 * @date : 2022/8/10 9:56
 * @Version: 1.0
 * @Desc : annotation......
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {
}
