package com.daiwei.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.daiwei.project.backend.system.model.User;

/**
 * shiro工具类
 * @author david
 *
 */
public class ShiroKit {
	

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static User getCurrentUser() {
		return (User)ShiroKit.getSessionAttribute("user");
	}

	public static long getUserId() {
		return getCurrentUser().getId();
	}

	
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
	
	public static String getKaptcha(String key) {
		Object kaptcha = getSessionAttribute(key);
		if(kaptcha == null){
			System.out.print("验证码已失效");
		}
		getSession().removeAttribute(key);
		return kaptcha.toString();
	}

}
