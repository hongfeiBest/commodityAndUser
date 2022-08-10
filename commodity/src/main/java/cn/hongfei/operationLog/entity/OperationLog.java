package cn.hongfei.operationLog.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author : hongfeiBest
 * @date : 2022/8/10 9:50
 * @Version: 1.0
 * @Desc : annotation......
 */
@Data
@TableName("operation_log")
public class OperationLog {
    @TableId(type= IdType.UUID)
    private String id;
    private String userId;
    private String userName;
    private String operationName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
