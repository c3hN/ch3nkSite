package com.ch3nk.ch3nkSite.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component("loggers")
public class Loggers {

    //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
    @Pointcut("execution(public * com.ch3nk.ch3nkSite.modules.sys.controller.*.*(..))")
    public void aspect() {}


    @Before("aspect()")
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println("==============================before==============================");
        System.out.println(joinPoint.getSignature().getName());
        System.out.println("==============================before==============================");
    }


    @After("aspect()")
    public void afterMethod(JoinPoint joinPoint) {
        System.out.println("==============================after==============================");
        System.out.println(joinPoint.getSignature().getName());
        System.out.println("==============================after==============================");

    }



}


