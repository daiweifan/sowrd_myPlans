package com.daiwei.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 
* @ClassName: ShrioConfiguration 
* @Description: 
* @author daiwei
* @date 2017年6月28日 下午1:45:39 
* 
* @version V2.0
 */
@Configuration
public class ShiroConfiguration {
	 private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

	    
		@Bean
		public EhCacheManager ehcacheManager(){
			EhCacheManager ehcacheManager = new EhCacheManager();
			ehcacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
			return ehcacheManager;
		}

		@Bean(name = "myShiroRealm")
		public MyShiroRealm myShiroRealm(EhCacheManager ehCacheManager){
			MyShiroRealm realm = new MyShiroRealm();
			realm.setCacheManager(ehCacheManager);
			return realm;
		}

		@Bean(name = "lifecycleBeanPostProcessor")
		public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
			return new LifecycleBeanPostProcessor();
		}

		@Bean
		public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
			DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
			creator.setProxyTargetClass(true);
			return creator;
		}

		@Bean(name = "securityManager")
		public DefaultWebSecurityManager defaultWebSecurityManager(MyShiroRealm realm){
			DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
			securityManager.setRealm(realm);
			securityManager.setCacheManager(ehcacheManager());
			return securityManager;
		}

		@Bean
		public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
			AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
			advisor.setSecurityManager(securityManager);
			return advisor;
		}

	    /**
	     * ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。
	     * 它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
	     */
	    @Bean(name = "shiroFilter")
	    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
	        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
	        logger.info("shiro开始进行拦截");
	        shiroFilterFactoryBean.setSecurityManager(securityManager);
	        LogoutFilter logoutFilter = new LogoutFilter();
	        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
	        logoutFilter.setRedirectUrl("/backend/system/login");
	        shiroFilterFactoryBean.setLoginUrl("/backend/system/login");
	        //登录成功后要跳转的连接,逻辑也可以自定义，
	        shiroFilterFactoryBean.setSuccessUrl("/backend/system/dashboard");
	       //用户访问未对其授权的资源时,所显示的连接
	        shiroFilterFactoryBean.setUnauthorizedUrl("/backend/system/unauthorized");
	        loadShiroFilterChain(shiroFilterFactoryBean);
	        return shiroFilterFactoryBean;
	    }


		/**
		 * 加载ShiroFilter权限控制规则
		 */
		private void loadShiroFilterChain(ShiroFilterFactoryBean factoryBean) {
			/**下面这些规则配置最好配置到配置文件中*/
			  Map<String, String> filterChainDefinitionManager = new LinkedHashMap<String, String>();
		        filterChainDefinitionManager.put("/backend/system/login", "anon");
		        filterChainDefinitionManager.put("/test/**", "anon");
		        filterChainDefinitionManager.put("/backend/system/logout", "anon");
		        filterChainDefinitionManager.put("/backend/system/loginPost", "anon");
		        filterChainDefinitionManager.put("/backend/system/**", "authc");
		        filterChainDefinitionManager.put("/backend/system/upload", "anon");
		        filterChainDefinitionManager.put("/backend/system/register", "anon");
//		        filterChainDefinitionManager.put("/user/edit/**", "authc,perms[user:edit]");// 这里为了测试，固定写死的值，也可以从数据库或其他配置中读取
		        filterChainDefinitionManager.put("/**", "anon");
		        filterChainDefinitionManager.put("/backend/system/unauthorized", "anon");
			factoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);
		}


	}

