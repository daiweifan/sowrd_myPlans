package com.daiwei.project.backend.system.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.daiwei.common.node.MenuNode;
import com.daiwei.project.backend.system.model.Role;
import com.daiwei.project.backend.system.model.User;
import com.daiwei.utils.ShiroKit;

import cn.hutool.core.lang.Console;
import springfox.documentation.annotations.ApiIgnore;

/** * @author  david:
 * @date 创建时间：2016年5月27日 下午3:12:58
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@ApiIgnore
@RestController
@RequestMapping("/backend/system")
public class BackendController extends BaseController{	
	
	
	public static final String viewPath = "backend/system/";
	
	
	/**
	 * 后台主页
	 * 方法功能说明
	 * 创建时间 2017年6月27日 下午8:48:08
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping("/dashboard")
	public ModelAndView homepage(){
		ModelAndView mav = new ModelAndView();
		User user = userService.userDao.getOne(ShiroKit.getUserId());
		List<Role> roleList = user.getRoles();
	    if (roleList == null || roleList.size() == 0) {
            ShiroKit.logout();
            mav.addObject("tips", "该用户没有角色，无法登陆");
            mav.setViewName(viewPath+"login");
            return mav;
        }
	    //默认第一个角色  暂时考虑一个角色
	    List<MenuNode> menus = menuService.getMenusByRoleId(roleList.get(0).getId());
	    mav.addObject("menus", menus);
		mav.addObject("roleName", user.getRolesName().toString());
		mav.addObject("user", user);
		mav.addObject("avatar", user.getAvatar()==null?  swordConfig.getCtx()+"/img/profile_small.jpg":swordConfig.getCtx()+user.getAvatar().getUrl());
		Console.log(swordConfig.getCtx()+user.getAvatar().getUrl());
		mav.setViewName(viewPath+"layout");//dashboard页面内嵌在里面
		return mav;
	}
	
	
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewPath+"index");//dashboard页面内嵌在里面
		return mav;
	}

	/**
	 * 
	 * 方法功能说明
	 * 创建时间 2017年6月28日 下午10:10:25
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping("/unauthorized")
	public ModelAndView unauthorized(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("unauthorized");
		return mav;
	}
	
}
