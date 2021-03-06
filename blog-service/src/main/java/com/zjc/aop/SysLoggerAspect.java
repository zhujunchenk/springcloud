/**
 * @program: springcloud
 * @description:
 * @author: zhujunchen
 * @create: 2019-10-13
 **/
package com.zjc.aop;

import com.alibaba.fastjson.JSON;
import com.zjc.annotation.SysLogger;
import com.zjc.entity.SysLog;
import com.zjc.service.LoggerService;
import com.zjc.util.HttpUtils;
import com.zjc.util.UserUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect//开启切面
@Component
public class SysLoggerAspect {

    @Autowired
    private LoggerService loggerService;

    //声明一个切点,切点为@SysLogger注解
    @Pointcut("@annotation(com.zjc.annotation.SysLogger)")
    public void loggerPointCut(){

    }

    //通过@Before注解,表明在进入切点之前进行拦截
    @Before("loggerPointCut()")
    public void saveSysLog(JoinPoint joinPoint){
        MethodSignature signature= (MethodSignature) joinPoint.getSignature();
        Method method=signature.getMethod();

        SysLog sysLog=new SysLog();
        SysLogger sysLogger=method.getAnnotation(SysLogger.class);
        if (sysLogger !=null){
            //注解上的描述
            sysLog.setOperation(sysLogger.value());
        }
        //请求的方法名
        String className=joinPoint.getTarget().getClass().getName();
        String methodName=signature.getName();
        sysLog.setMethod(className+"."+methodName+"()");
        //请求的参数
        Object[] args=joinPoint.getArgs();
        String params="";
        for (Object obj : args) {
            params+= JSON.toJSONString(obj);
        }
        if (!StringUtils.isEmpty(params)){
            sysLog.setParams(params);
        }
        //设置IP地址
        sysLog.setIp(HttpUtils.getIpAddress());
        //用户名
        String username= UserUtils.getCurrentPrinciple();
        if (!StringUtils.isEmpty(username)){
            sysLog.setUsername(username);
        }
        sysLog.setCreateDate(new Date());
        //保存系统日志
        loggerService.log(sysLog);
    }
}

