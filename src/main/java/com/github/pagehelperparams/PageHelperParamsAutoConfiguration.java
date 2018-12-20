package com.github.pagehelperparams;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;

/**
 * TODO spring boot 自动配置类
 * 
 * @author huangYu
 * @version 1.0
 * @createTime 2018年12月18日 下午4:28:26
 */
@Configuration
@ConditionalOnBean({ SqlSessionFactory.class, PageHelperAutoConfiguration.class })
@EnableConfigurationProperties(PageHelpProperties.class)
@AutoConfigureAfter(PageHelperAutoConfiguration.class)
public class PageHelperParamsAutoConfiguration {

	@Autowired
	private PageHelpProperties properties;

	@Bean
	public PageHelperBeanProcessPost pageHelperBeanProcessPost() {
		Assert.notNull(properties, PageHelpProperties.class.getName() + "must not be null");
		PageHelperBeanProcessPost beanProcessPost = new PageHelperBeanProcessPost(properties);
		return beanProcessPost;
	}

}
