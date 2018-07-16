package com.daiwei.project.backend.system.controller;

import java.io.IOException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.daiwei.common.log.LogAop;
import com.daiwei.utils.Result;
import com.daiwei.utils.ShiroKit;

/**
 * 登录相关
* @ClassName: LoginController 
* @Description: 
* @author daiwei
* @date 2017年7月11日 下午4:26:42 
* 
* @version V2.0
 */
@Controller
@RequestMapping(value = "/backend/system")
public class LoginController extends BaseController {

	public static final String viewPath = "backend/system/";
	
	/**
	 * 登录页面
	 * 方法功能说明
	 * 创建时间 2017年6月27日 下午8:48:08
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping("/login")
	public ModelAndView login(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewPath+"login");
		return mav;
	}
	
	/**
	 * 登录
	 */
	@LogAop(action="登录",targetType="user",remark="用户登录")
	@ResponseBody
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public Result loginPost(String username, String password, String captcha)throws IOException {
		
		try{
			Subject subject = ShiroKit.getSubject();
			//sha256加密
			password = new Sha256Hash(password).toHex();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
		}catch (UnknownAccountException e) {
			return Result.error(e.getMessage());
		}catch (IncorrectCredentialsException e) {
			return Result.error(e.getMessage());
		}catch (LockedAccountException e) {
			return Result.error(e.getMessage());
		}catch (AuthenticationException e) {
			return Result.error("账户验证失败");
		}
	    
		return Result.ok("登录成功！");
	}
	
	/**
	 * 退出
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		ShiroKit.logout();
		return "redirect:login.html";
	}
	
}
