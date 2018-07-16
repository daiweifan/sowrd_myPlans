package com.daiwei.project.backend.system.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.daiwei.common.log.LogAop;
import com.daiwei.project.backend.system.model.User;
import com.daiwei.utils.BeanKit;
import com.daiwei.utils.Result;
import com.daiwei.utils.ShiroKit;
import com.daiwei.utils.Utils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/** * @author  david:
 * @date 创建时间：2016年6月4日 下午4:21:11
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@RestController
@RequestMapping("/backend/system/user")
@Api(value = "User")  
public class UserController extends BaseController {
	
	public static final String viewPath = "backend/system/user/";
	/**
	 * 展示用户
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:54:21
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping("/show")
	public ModelAndView show(){
		ModelAndView mav = new ModelAndView();
		Map<String,Object> user = BeanKit.describe(ShiroKit.getSessionAttribute("user"));
		User userEntity = userService.userDao.findOne(Long.valueOf(user.get("id").toString()));
		mav.addObject("birthday", user.get("birthday").toString());
		mav.addObject("roleName", userEntity.getRolesName().toString());
		mav.addObject("url", userEntity.getAvatar()==null?"":userEntity.getAvatar().getUrl());
		/*	mav.addObject("sexValue", userEntity.getSex()==1?"男":"女");*/
		mav.setViewName(viewPath+"show");
		return mav;
	}
	
	/**
	 * 用户首页
	* @Description:  
	* @author david  
	* @date 2017年7月20日 下午4:18:22
	 */
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewPath+"index");
		return mav;
	}

	/**
	 * 新增用户
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:56:11
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping("/add")
	public ModelAndView add(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewPath+"add");
		return mav;
	}
	
	/**
	 * 修改用户信息
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:55:54
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping("/edit/{userId}")
	public ModelAndView edit(@PathVariable Long userId){
		ModelAndView mav = new ModelAndView();
		User user = userService.userDao.findOne(userId);
		mav.addObject("user", user);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		mav.addObject("birthday", sdf.format(user.getBirthday()));
		mav.addObject("roleName", 	user.getRoles().isEmpty() ? " ":user.getRoles().get(0).getName());
		mav.setViewName(viewPath+"edit");
		return mav;
	}
	
	/**
	 * 用户列表
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:55:42
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@ApiOperation(value = "获取用户列表，支持分页", notes = "json方法获取用户列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "string"),
		@ApiImplicitParam(name = "beginTime", value = "开始时间", required = true, dataType = "string") })
	@RequestMapping("/list")
    @ResponseBody
	public List<Map<String,Object>> list(@RequestParam Map<String,Object> map,@RequestParam(required = false) String name, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer deptid){
		List<Map<String,Object>> list = new ArrayList<>();
		
		List<User> userList = (List<User>) userService.userDao.findAll();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(User user:userList){
			Map<String,Object> mapTemp = BeanKit.describe(user, "roles","avatar");
			mapTemp.put("sexName",user.getSex()==1?"男":"女");
			mapTemp.put("roleName",user.getRolesName());
			mapTemp.put("statusName", user.getState());
			mapTemp.put("createTime", sdf.format(user.getCreateTime()));
			list.add(mapTemp);
		}

		return list;
	}
	/**
	 * 修改密码
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:52:53
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping("/changePwd")
	public ModelAndView changePwd(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewPath+"changePwd");
		return mav;
	}
	
	
	/**
	 * 修改密码
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:53:57
	 * 开发者 david
	 * @参数： @param map
	 * @参数： @return	
	 * @return： Map<String,Object>
	 */
	@LogAop(action="修改",remark="删除密码",targetType="user")
	@ApiOperation(value = "删除密码", notes = "修改用户密码")
	@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
	@ResponseBody
	@RequestMapping(value = "/updatePwd")
	public Map<String,Object> updatePwd(@RequestParam Map<String,Object> map) {
		Map<String,Object> tmap = new HashMap<String,Object>();
		
		return tmap;
	}
	
	/**
	 * 删除
	 * 方法功能说明
	 * 创建时间 2017年7月25日 下午10:59:17
	 * 开发者 david
	 * @参数： @param ids
	 * @参数： @return	
	 * @return： Result
	 */
	@LogAop(action="删除",remark="删除用户",targetType="user")
	@ApiOperation(value = "删除用户", notes = "根据url的ids来指定删除对象或者对象集")
	@ApiImplicitParam(name = "ids", value = "用户ids", required = true, dataType = "String")
	@ResponseBody
	@RequestMapping(value = "/delete")
    public Result delete(String ids) {
        List<Long> list = Utils.stringToLongList(ids, ",");
		for(Long id:list){
			//物理删除
			userService.userDao.delete(id);
		}
		return Result.ok("删除成功!");
	}
	
	/**
	 * 保存用户
	 * 方法功能说明
	 * 创建时间 2017年8月22日 下午10:36:42
	 * 开发者 david
	 * @参数： @param user
	 * @参数： @return	
	 * @return： Result
	 */
	@LogAop(action="新增",remark="新增用户",targetType="user")
	@ApiOperation(value = "新增保存用户", notes = "根据User对象操作用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@ResponseBody
    @RequestMapping(value="/save")
    public Result save(User user){
        return  userService.save(user);	
    }
	
	/**
	 * 修改用户
	 * 方法功能说明
	 * 创建时间 2017年8月22日 下午10:36:42
	 * 开发者 david
	 * @参数： @param user
	 * @参数： @return	
	 * @return： Result
	 */
	@LogAop(action="修改",remark="修改用户",targetType="user")
	@ApiOperation(value = "修改企业", notes = "根据User对象操作用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@ResponseBody
    @RequestMapping(value="/update")
    public Result update(User user){
        return  userService.save(user);	
    }
	
	/**
	 * 个人资料修改
	 * 方法功能说明
	 * 创建时间 2017年9月7日 下午8:48:44
	 * 开发者 david
	 * @参数： @param map
	 * @参数： @return	
	 * @return： Result
	 */
	@LogAop(action="修改",remark="修改个人资料",targetType="user")
	@ApiOperation(value = "修改企业", notes = "根据User对象操作用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@ResponseBody
    @RequestMapping(value="/updateShow")
    public Result updateShow(@RequestParam Map<String,Object> map){
        return  userService.updateShow(map);	
    }

	
	
	/**
	 * 展示用户
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:54:21
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping("/roleAssign/{userId}")
	public ModelAndView roleAssign(@PathVariable Long userId){
		ModelAndView mav = new ModelAndView();
		User user = userService.userDao.findOne(userId);
		mav.addObject("user", user);
		mav.addObject("birthday", user.getBirthday()==null?"":user.getBirthday().toString());
		mav.setViewName(viewPath+"roleAssign");
		return mav;
	}
	
	
	/**
	 * 用户分配角色
	 * 方法功能说明
	 * 创建时间 2017年9月5日 下午8:37:00
	 * 开发者 david
	 * @参数： @param userId
	 * @参数： @param roleIds
	 * @参数： @return	
	 * @return： Tip
	 */
	@RequestMapping("/setRole")
	@ResponseBody
    public Result setRole(@RequestParam("userId") long userId, @RequestParam("roleIds") String roleIds) {
        return userService.setRoles(userId, roleIds);
    }
}
