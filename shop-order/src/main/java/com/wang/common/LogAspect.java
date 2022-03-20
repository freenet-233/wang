package com.wang.common;

import com.google.gson.Gson;
import com.wang.annotation.Log;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.jboss.logging.NDC;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Project: wang
 * Package: com.wang.common
 * <p>
 * User: Robin Wang
 * Date: 2022/3/20
 * Time: 21:30
 * Describe:
 * <p>
 * Created with IntelliJ IDEA
 */

@Aspect
@Component
//@Slf4j
public class LogAspect {

    private Logger logger = LogManager.getLogger(LogAspect.class);

    @Pointcut("execution(* com.wang.controller.*.*(..))")
    public void log(){
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("----------------doBefore---------------");
        Gson gson = new Gson();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        MDC.put("uri", request.getRequestURI());
        NDC.push(request.getRemoteAddr());


        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request: {}, params: {}", requestLog, gson.toJson(args));

    }

    @After("log()")
    public void doAfter(JoinPoint joinPoint){
        logger.info("----------------doAfter---------------");
    }

    @AfterReturning(pointcut = "@annotation(logAnnotation)", returning = "jsonResult")
    void afterLogReturn(JoinPoint joinPoint, Log logAnnotation, Object jsonResult) {
        logger.info("一个Log注解的通知:{}", logAnnotation.msg());
    }

//    @AfterReturning(returning = "result", pointcut = "log()")
//    public void doAfterReturn(JoinPoint joinPoint, Object result){
//        logger.info("----------------doAfterReturn---------------");
//        Gson gson = new Gson();
//        logger.info(gson.toJson(result));
//    }

    @AfterThrowing(pointcut = "log()", throwing = "throwing")
    private void doAfterThrowing(JoinPoint joinPoint, Exception throwing){
        logger.info("----------------doAfterThrowing---------------");
    }

    @Around(value = "log()", argNames = "pjp")
    private Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        Long endTime = System.currentTimeMillis();
        logger.info("Around方法进入,用时{}", endTime - startTime);
        return proceed;
    }

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;
    }

//    @Around(value = "log()")
//    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        String className = proceedingJoinPoint.getTarget().getClass().getName();
//
//        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
//        Method method = signature.getMethod();
//
//        Object[] args = proceedingJoinPoint.getArgs();
//        Gson gson = new Gson();
//        log.info("{}, {}()执行，Params ==> {}", className, method.getName(), gson.toJson(args));
//
//        Object proceed = proceedingJoinPoint.proceed();
//        log.info("{}, {}()执行，Returns ==> {}", className, method.getName(), gson.toJson(proceed));
//        return proceed;
//    }
}
