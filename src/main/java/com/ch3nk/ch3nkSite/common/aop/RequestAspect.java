package com.ch3nk.ch3nkSite.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect
@Component("requestAspect")
public class RequestAspect {



    @Pointcut("execution(public * com.ch3nk.ch3nkSite.modules.*.controller.*.*(..))")
    public void pointCut(){}

//    @Before("pointCut()")
//    public void preHand(JoinPoint joinPoint){
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String uri = request.getRequestURI();
//
//        System.out.println("========================================================================================================================================");
//        System.out.println("uri"+request.getRequestURI());
//        System.out.println("url"+request.getRequestURL());
//        System.out.println("========================================================================================================================================");
//        Enumeration<String> headerNames = request.getHeaderNames();
////        while (headerNames.hasMoreElements()) {
////            System.out.println("requeset head name : "+headerNames.nextElement());
////        }
//        System.out.println(request.getHeader("host"));
//        System.out.println(request.getHeader("connection"));
//        System.out.println(request.getHeader("accept"));
//        System.out.println(request.getHeader("x-requested-with"));
//        System.out.println(request.getHeader("user-agent"));
//        System.out.println(request.getHeader("content-type"));
//        System.out.println(request.getHeader("referer"));
//        System.out.println(request.getHeader("accept-encoding"));
//        System.out.println(request.getHeader("accept-language"));
//        System.out.println(request.getHeader("cookie"));
//        System.out.println("========================================================================================================================================");
//        System.out.println("Signature     "+joinPoint.getSignature());
//        System.out.println("Signature.name     "+joinPoint.getSignature().getName());
//        System.out.println("Signature.DeclaringTypeName()     "+joinPoint.getSignature().getDeclaringTypeName());
//        System.out.println("Signature.DeclaringType()     "+joinPoint.getSignature().getDeclaringType());
//        System.out.println("Signature.Modifiers()()     "+joinPoint.getSignature().getModifiers());
//        System.out.println("========================================================================================================================================");
//        System.out.println("args  "+joinPoint.getArgs());
//        System.out.println("kind  "+joinPoint.getKind());
//        System.out.println("SourceLocation  "+joinPoint.getSourceLocation());
//        System.out.println("StaticPart  "+joinPoint.getStaticPart());
//        System.out.println("Target  "+joinPoint.getTarget());
//        System.out.println("========================================================================================================================================");
//    }



    @Before("pointCut()")
    public void preHandle(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        System.out.println(joinPoint.getTarget());
    }

}

