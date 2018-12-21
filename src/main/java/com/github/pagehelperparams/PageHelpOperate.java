package com.github.pagehelperparams;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.Configuration;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author :huangyu
 * @Date : 10:36 2018/12/21
 */
@Aspect
public class PageHelpOperate {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private PageHelpProperties properties;

    private Configuration configuration;

    private Map<Object,PageHelpAspect> pageHelpAspectMap=new ConcurrentHashMap<Object, PageHelpAspect>(100);

    public PageHelpOperate(PageHelpProperties pageHelpProperties,Configuration configuration){
        this.properties=pageHelpProperties;
        this.configuration=configuration;
    }

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point)throws Throwable{
        try {
            Object target=point.getTarget();
            PageHelpAspect aspect=pageHelpAspectMap.get(target);
            if (aspect == null) {
                aspect=new PageHelpAspect(target,properties,configuration);
                pageHelpAspectMap.put(target,aspect);
            }
            Object []  args=point.getArgs();
            Signature signature=point.getSignature();
            if (!(signature instanceof MethodSignature)){
                throw new RuntimeException("@service 只能用于类上面");
            }
            MethodSignature methodSignature=(MethodSignature) signature;
            Method currMethod=target.getClass().getMethod(methodSignature.getName(),methodSignature.getParameterTypes());
            aspect.setPageInfo(currMethod,args);
            return point.proceed();
        }finally {
            PageHelper.clearPage();
        }
    }
}
