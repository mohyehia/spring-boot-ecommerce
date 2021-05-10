package com.mohyehia.ecommerce.aop;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Component
@Aspect
public class AppLogger {
    private final Log log = LogFactory.getLog(this.getClass());

    @Around("execution(* com.mohyehia.ecommerce.controller..*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(joinPoint.getTarget().getClass().getName()
                + " :: " +
                joinPoint.getSignature().getName() + " :: start");
        log.info("executing function " +
                joinPoint.getSignature().getName() +
                " with arguments = " +
                Arrays.toString(joinPoint.getArgs()));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object returnedValue = joinPoint.proceed();
        stopWatch.stop();
        log.info(joinPoint.getTarget().getClass().getName()
                + " :: " +
                joinPoint.getSignature().getName() + " :: execution time is =>" + stopWatch.getTotalTimeMillis() + " ms");
        log.info(joinPoint.getTarget().getClass().getName()
                + " :: " +
                joinPoint.getSignature().getName() + " :: end");
        return returnedValue;
    }
}
