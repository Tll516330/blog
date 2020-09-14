package cn.tll.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tll
 * @create 2020/9/14 10:14
 * @Aspect  切面  AOP
 * @Component 组件扫描
 */
@Aspect
@Component
public class LogAspect {

    /**
     * 声明日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 在切面之前执行
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        //获取Request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request:{}",requestLog);
    }
    /**
     * 哪些包控制
     */
    @Pointcut("execution(* cn.tll.controller.*.*(..))")
    public void log(){

    }

    /**
     * 在切面之后执行
     */
    @After("log()")
    public void doAfter(){
        logger.info("----------After-----------");
    }

    /**
     * 执行方法后放回的内容
     */
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("Result:{}",result);
    }

    @Data
    @AllArgsConstructor
    @ToString
    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;
    }
}
