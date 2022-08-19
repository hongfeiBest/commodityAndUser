package cn.hongfei.aspectj;

import cn.hongfei.annotation.Log;
import cn.hongfei.operationLog.entity.OperationLog;
import cn.hongfei.operationLog.service.impl.OperationLogServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;


@Component
@Aspect
public class Aspectj {

    @Autowired
    private OperationLogServiceImpl operationLogService;

    //commodityController层切点
    @Pointcut("execution(* cn.hongfei.commodity.controller.*.*(..))")
    public void commodityControllerAspect() {
    }


    /**
     * 前置通知，校验是否登录
     *
     */
    @Before("commodityControllerAspect()")
    public void doLoginBefore(){
        System.out.println("==========执行commodityController前置通知===============");
        //获取当前登录的用户
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        Object userName = request.getSession().getAttribute("userName");
        if (userName == null){
            System.out.println("当前用户未登录~~~请先登录~~~");
        }
    }


    /**
     * 含有@Log注解的方法正常返回后执行
     *
     * @param joinPoint 方法的路径，如：cn.hongfei.commodity.controller.add
     * @param log       自定义的注解
     * @param returnObj 方法返回的内容
     */
    @AfterReturning(pointcut = "@annotation(log)",returning = "returnObj")
    public void doLogAfterReturning(JoinPoint joinPoint,Log log,Object returnObj){
        System.out.println("==========执行LogAfterReturning方法正常返回后置通知："+log.title()+"===============");
        OperationLog operationLog = new OperationLog();
        //获取当前登录的用户
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        Object userName = request.getSession().getAttribute("userName");
        //operationLog.setUserId();
        if (userName != null){
            operationLog.setUserName(userName.toString());
        }
        operationLog.setOperationName(log.title());
        operationLogService.save(operationLog);
    }
}
