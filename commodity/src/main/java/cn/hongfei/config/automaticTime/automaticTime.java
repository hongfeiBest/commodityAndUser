package cn.hongfei.config.automaticTime;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.assertj.core.util.DateUtil;
import org.springframework.stereotype.Component;

/**
 * 实现自动填充创建时间和修改时间
 */
//@Component
//public class automaticTime implements MetaObjectHandler {
//    @Override
//    public void insertFill(MetaObject metaObject) {
//        this.setFieldValByName("createTime", DateUtil.now(), metaObject);
//        this.setFieldValByName("updateTime", DateUtil.now(), metaObject);
//    }
//
//    @Override
//    public void updateFill(MetaObject metaObject) {
//        this.setFieldValByName("updateTime", DateUtil.now(), metaObject);
//    }
//}
