package com.github.pagehelperparams;

import java.lang.reflect.Proxy;

import org.apache.ibatis.session.Configuration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

/**
 * TODO 主要是生成动态代理 凡是被{@link Service service} 标记的类都会被代理
 * 
 * @author huangYu
 * @version 1.0
 * @createTime 2018年12月18日 下午3:20:52
 */
public class PageHelperBeanProcessPost implements BeanPostProcessor {

	private PageHelpProperties properties;

	private Configuration configuration;

	public PageHelperBeanProcessPost(PageHelpProperties properties,Configuration configuration) {
		this.properties = properties;
		this.configuration=configuration;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		Class class1 = bean.getClass();
		if (AnnotationUtils.isAnnotationDeclaredLocally(Service.class, class1)) {
			return Proxy.newProxyInstance(class1.getClassLoader(), class1.getInterfaces(),
					new PageHelpProxy<>(bean, this.properties,this.configuration));
		}
		return bean;
	}

}
