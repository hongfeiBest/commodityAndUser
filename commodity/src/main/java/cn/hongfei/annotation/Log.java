package cn.hongfei.annotation;

import java.lang.annotation.*;

//@Target用来表示注解作用范围，超过这个作用范围，编译的时候就会报错。
//@Retention：注解的保留位置
//@Document：说明该注解将被包含在javadoc中
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块名称
     */
    String title() default "";
}
