package com.wjh.ssm.controller;

import com.wjh.ssm.domain.SysLog;
import com.wjh.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime;//开始时间
    private Class clazz;//访问的类
    private Method method;//访问的方法

    private String methodName;//方法的名称（带参数）


//前置通知 //主要是获取开始时间，执行的类是哪一个，执行的是哪一个方法
    @Before("execution(* com.wjh.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();//当前时间就是开始访问的时间
        clazz = jp.getTarget().getClass(); //具体要访问的类
        String name = jp.getSignature().getName();//获取访问的方法的名称
        Object[] args = jp.getArgs();//获取访问的方法的参数

        //获取具体执行的方法的method对象
        if (args==null || args.length==0){
            method =  clazz.getMethod(name);//只能获取无参数的方法
            methodName="（空参方法）";
        }else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            String sssss = "";
            method =  clazz.getMethod(name,classArgs);
            for (int i = 0; i < classArgs.length; i++) {
                sssss += "参数"+i+1+"类型为："+classArgs[i].getName()+"值为： "+args[i];
            }
            methodName = sssss;
        }



    }

    //后置通知
    @After("execution(* com.wjh.ssm.controller.*.*(..))")
    public void doafter(JoinPoint jp) throws Exception {
        //获取访问的时长 新建date的时间-方法执行前的时间=方法执行用时
        Long time = new Date().getTime()-visitTime.getTime();
        StringBuilder url = new StringBuilder("");
        //获取url
        if (clazz!=null && method!= null && clazz!=LogAop.class){
            //1.获取类上的@RequestMapping（/"orders"）
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation!=null){

                String[] classValue = classAnnotation.value();
                //如果检测到syslog这个类，不添加到日志
                if (classValue[0].equals("/sysLog")){
                    return;
                }
                //2.获取方法上的@RequestMapping值
                RequestMapping methodannotation = method.getAnnotation(RequestMapping.class);
                if (methodannotation!=null){
                        String[] methodValue = methodannotation.value();
                   url.append(classValue[0]).append(methodValue[0]);


                    //获取访问的ip
                    String ip = request.getRemoteAddr();

                    //获取用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取当前登录的用户
                    User user = (User)context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    //将相关日志信息对象封装到syslog里
                    SysLog  sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名] "+clazz.getName()+"[方法名] "+method.getName()+methodName);
                    sysLog.setUrl(url.toString());
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);


                    //调用service完成插入
                    sysLogService.save(sysLog);



                }


            }


        }

    }
}
