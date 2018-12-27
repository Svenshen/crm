/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.crm;

import com.szh.crm.domain.User;
import com.szh.crm.domain.log.Weblog;
import com.szh.crm.service.log.WeblogService;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-12-21 13:15:03
 */
@Aspect
@Component
public class WebLogAspect {

    @Autowired
    WeblogService weblogService;
    
    
    
    @Pointcut("execution(public * com.szh.crm.controller.*.*(..))")
    public void webLog(){
        
    }
    
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        //收到请求，记录请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        
        HttpServletRequest request = attributes.getRequest();
        //记录请求
        Weblog weblog = new Weblog();
        weblog.setIp(request.getRemoteAddr());
        weblog.setHttpmethod(request.getMethod());
        weblog.setUrl(request.getRequestURL().toString());
        weblog.setUserinfo(request.getRemoteUser());
        weblog.setClassmethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        weblog.setCreatetime(new Date());
//        log.info("URL : "+request.getRequestURL());
//        log.info("HTTP_METHOD : "+request.getMethod());
//        log.info("IP : "+ request.getRemoteAddr());
//        log.info("USER : "+request.getRemoteUser());
        Enumeration<String> en = request.getParameterNames();
        String httpvalue = "";
        while (en.hasMoreElements()) {
            String name = (String) en.nextElement();
            //log.info("name: "+name+" ,value: "+request.getParameter(name));
            httpvalue+="name: "+name+" ,value: "+request.getParameter(name)+";";
        }
        weblog.setHttpvalue(httpvalue);
        weblogService.add(weblog);
    }
    
    
    @AfterReturning(returning = "ret" , pointcut = "webLog()")
    public void doAfterReturning(JoinPoint joinPoint,Object ret) throws Throwable{
        //处理完请求，返回内容
        
//        log.info("RESPONSE : "+ ret);
    }
}
