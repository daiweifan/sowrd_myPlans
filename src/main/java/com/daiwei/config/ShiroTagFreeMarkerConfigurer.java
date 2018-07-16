package com.daiwei.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.jagregory.shiro.freemarker.ShiroTags;

import freemarker.template.Configuration;
/**
 * shiro标签的配置
* @ClassName: ShiroTagFreeMarkerConfigurer 
* @Description: 
* @author daiwei
* @date 2017年7月17日 下午4:50:46 
* 
* @version V2.0
 */
@Component
public class ShiroTagFreeMarkerConfigurer implements InitializingBean {

	@Autowired
	private Configuration configuration;


	@Override
	public void afterPropertiesSet() throws Exception {
		// 加上这句后，可以在页面上使用shiro标签
		configuration.setSharedVariable("shiro", new ShiroTags());
	}
}
