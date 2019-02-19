///*
//package com.ch3nk.ch3nkSite.common.aop;
//
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysOperation;
//import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
//import com.ch3nk.ch3nkSite.modules.sys.mapper.SysOperationMapper;
//import com.ch3nk.ch3nkSite.modules.sys.service.ISysOperationService;
//import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
//import eu.bitwalker.useragentutils.UserAgent;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.shiro.SecurityUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Date;
//
//@Aspect
//@Component("requestAspect")
//public class RequestAspect {
//
//    @Autowired
//    private ISysOperationService sysOperationService;
//
//    @Autowired
//    private SysOperationMapper sysOperationMapper;
//
//    @Pointcut("execution(public * com.ch3nk.ch3nkSite.modules.*.controller.*.*(..))")
//    public void pointCut(){}
//
//    @Before("pointCut()")
//    public void preHandle(JoinPoint joinPoint) {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
//        SysUser sysUser = (SysUser)SecurityUtils.getSubject().getPrincipal();
//        SysOperation sysOperation = new SysOperation();
//        sysOperation.setId(UUIDutil.getUUID());
//        if (sysUser != null && StringUtils.isNotEmpty(sysUser.getUserId())) {
//            sysOperation.setUserId(sysUser.getUserId());
//        }
//        sysOperation.setReqIP(request.getRemoteAddr());          //请求来源ip
//        sysOperation.setReqResource(request.getRequestURI());
//        sysOperation.setClientType(userAgent.getOperatingSystem().toString());
//        sysOperation.setBrowserName(userAgent.getBrowser().toString());
//        sysOperation.setBrowserVersion(userAgent.getBrowserVersion().toString());
//        sysOperation.setCreateDate(new Date());
////        sysOperationServiceImpl.saveOperate(sysOperation);
//        sysOperationMapper.insertSelective(sysOperation);
//    }
//
////    @Before("pointCut()")
////    public void preHand(JoinPoint joinPoint){
////        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
////        String uri = request.getRequestURI();
////        String header = request.getHeader("User-Agent");
////        System.out.println("========================================================================================================================================");
////        System.out.println("uri"+request.getRequestURI());
////        System.out.println("url"+request.getRequestURL());
////        System.out.println("User-Agent:      "+header);
////        System.out.println("========================================================================================================================================");
////        Enumeration<String> headerNames = request.getHeaderNames();
//////        while (headerNames.hasMoreElements()) {
//////            System.out.println("requeset head name : "+headerNames.nextElement());
//////        }
////        System.out.println(request.getHeader("host"));
////        System.out.println(request.getHeader("connection"));
////        System.out.println(request.getHeader("accept"));
////        System.out.println(request.getHeader("x-requested-with"));
////        System.out.println(request.getHeader("user-agent"));
////        System.out.println(request.getHeader("content-type"));
////        System.out.println(request.getHeader("referer"));
////        System.out.println(request.getHeader("accept-encoding"));
////        System.out.println(request.getHeader("accept-language"));
////        System.out.println(request.getHeader("cookie"));
////        System.out.println("========================================================================================================================================");
////        System.out.println("Signature     "+joinPoint.getSignature());
////        System.out.println("Signature.name     "+joinPoint.getSignature().getName());
////        System.out.println("Signature.DeclaringTypeName()     "+joinPoint.getSignature().getDeclaringTypeName());
////        System.out.println("Signature.DeclaringType()     "+joinPoint.getSignature().getDeclaringType());
////        System.out.println("Signature.Modifiers()()     "+joinPoint.getSignature().getModifiers());
////        System.out.println("========================================================================================================================================");
////        System.out.println("args  "+joinPoint.getArgs());
////        System.out.println("kind  "+joinPoint.getKind());
////        System.out.println("SourceLocation  "+joinPoint.getSourceLocation());
////        System.out.println("StaticPart  "+joinPoint.getStaticPart());
////        System.out.println("Target  "+joinPoint.getTarget());
////        System.out.println("========================================================================================================================================");
////    }
//
////    @After("pointCut()")
////    public void afterHand(JoinPoint joinPoint) {
////        sysOperation = new SysOperation();
////        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
////        String uri = request.getRequestURI();
////        sysOperation.setReqResource(uri);
////    }
//
////    @Around("pointCut()")
////    public Object aroundHandle(ProceedingJoinPoint proceedingJoinPoint) {
////        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
////        String uri = request.getRequestURI();
////        System.out.println("uri"+uri);
////        System.out.println("==================================================================================");
////        System.out.println("sourceLocation = "+proceedingJoinPoint.getSourceLocation());
////        System.out.println("Kind = "+proceedingJoinPoint.getKind());
////        System.out.println("====================================================================================");
////        Object result = null;
////        String methodName = proceedingJoinPoint.getSignature().getName();
////        System.out.println("args===="+proceedingJoinPoint.getArgs().toString());
////        System.out.println("====================================================================================");
////        System.out.println(methodName);
////        System.out.println("====================================================================================");
////        try {
////            //执行下一个通知或者执行目标方法
////            result =  proceedingJoinPoint.proceed();
////            System.out.println("==========================================================================================");
////            System.out.println("Target method result = "+result);
////            System.out.println("==========================================================================================");
////        } catch (Throwable throwable) {
////            throwable.printStackTrace();
////        }
////        return result;
////    }
//
//
//
//}
//
//*/
