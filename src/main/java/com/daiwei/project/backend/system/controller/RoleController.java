package com.daiwei.project.backend.system.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.daiwei.common.node.ZTreeNode;
import com.daiwei.project.backend.system.model.Role;
import com.daiwei.project.backend.system.model.User;
import com.daiwei.project.backend.system.service.RoleService;
import com.daiwei.utils.BeanKit;
import com.daiwei.utils.Result;
import com.daiwei.utils.Utils;


/**
 * @author  david:
 * @date 创建时间：2016年6月4日 下午4:21:11
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@RestController
@RequestMapping("/backend/system/role")
public class RoleController extends BaseController {
	
	public static final String viewPath = "backend/system/role/";
	
	
	@Autowired
	private RoleService roleService;
	
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
	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable long id){
		ModelAndView mav = new ModelAndView();
		Role role = roleService.roleDao.findOne(id);
		mav.addObject("role", role);
		mav.addObject("deptName", role.getDepartment()==null? "":role.getDepartment().getName());
		mav.setViewName(viewPath+"edit");
		return mav;
	}
	
	/**
	 * 角色列表
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:55:42
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping("/list")
    @ResponseBody
	public List<Map<String,Object>> list(@RequestParam Map<String,Object> map,@RequestParam(required = false) String name, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer deptid){
		List<Map<String,Object>> list = new ArrayList<>();
		List<Role> roleList =   (List<Role>) roleService.roleDao.findAll();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Role role:roleList){
			Map<String,Object> mapTemp = BeanKit.describe(role,"department","menus","users");
			mapTemp.put("createTime", sdf.format(role.getCreateTime()));
			mapTemp.put("deptName", role.getDepartment()==null? "":role.getDepartment().getName());
			list.add(mapTemp);
		}
		return list;
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
	@ResponseBody
	@RequestMapping(value = "/delete")
    public Result delete(String ids) {
        List<Long> list = Utils.stringToLongList(ids, ",");
        for(Long id:list){
        	roleService.roleDao.delete(id);
        }
		return Result.ok("删除成功!");
	}
	
	/**
	 * 修改
	 * 方法功能说明
	 * 创建时间 2017年8月6日 下午9:28:43
	 * 开发者 david
	 * @参数： @param role
	 * @参数： @return	
	 * @return： Result
	 */
	@ResponseBody
    @RequestMapping(value="/save")
    public Result save(Role role){
        return  roleService.save(role);	
    }
	
	
	/**
	 * 权限分配
	 * 方法功能说明
	 * 创建时间 2017年8月21日 下午11:07:26
	 * 开发者 david
	 * @参数： @param id
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping("/assign/{id}")
	public ModelAndView assign(@PathVariable long id){
		ModelAndView mav = new ModelAndView();
		Role role = roleService.roleDao.findOne(id);
		mav.addObject("role", role);
		mav.addObject("deptName", role.getDepartment()==null? "":role.getDepartment().getName());
		mav.setViewName(viewPath+"assign");
		return mav;
	}
	
	/**
	 * 根据用户获取角色列表
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/selectRoleTreeListByUserId/{id}")
    @ResponseBody
	public List<ZTreeNode> selectMenuTreeListByRoleId(@PathVariable long id){
		 List<ZTreeNode> list = new ArrayList<ZTreeNode>();
		 User user = userService.userDao.findOne(id);
		 List<Role> roles = user.getRoles();
		 List<Role> roleList = (List<Role>) roleService.roleDao.findAll();
		 for(Role role:roleList){
			 if(roles.contains(role)){
		    	 list.add(new ZTreeNode(role.getId(),0l,role.getName(),false,true));
			 }else{
		    	 list.add(new ZTreeNode(role.getId(),0l,role.getName(),false,false));
			 }
	     }
	  
	     return list;
	}
	
	/**
	 * 保存角色权限
	 * 方法功能说明
	 * 创建时间 2017年8月22日 下午8:53:59
	 * 开发者 david
	 * @参数： @param ids
	 * @参数： @param roleId
	 * @参数： @return	
	 * @return： Result
	 */
	@ResponseBody
    @RequestMapping(value="/setAuthority")
    public Result setAuthority(@RequestParam  String ids,@RequestParam long roleId){
        return  roleService.setAuthority(ids,roleId);	
    }
}
