package com.daiwei.project.backend.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daiwei.project.backend.system.dao.MenuDao;
import com.daiwei.project.backend.system.dao.RoleDao;
import com.daiwei.project.backend.system.model.Menu;
import com.daiwei.project.backend.system.model.Role;
import com.daiwei.utils.Result;
import com.daiwei.utils.Utils;

/** * @author  david:
 * @date 创建时间：2017年6月28日 下午9:16:07
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@Service
public class RoleService  {
	
	@Autowired
	public RoleDao roleDao;
	
	@Autowired
	public MenuDao menuDao;

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Role findByName(String name) {
		return roleDao.findByName(name);
	}
	
	/**
	 * 保存角色
	* @Description:  
	* @author david  
	* @date 2017年7月21日 下午3:12:53
	 */
	public Result save(Role role){
		 roleDao.save(role);
		 return new Result(1,"保存成功！",null);
    }
	
	
	/**
	 * 保存角色的菜单
	 * 方法功能说明
	 * 创建时间 2017年8月21日 下午11:32:10
	 * 开发者 david
	 * @参数： @param ids
	 * @参数： @param roleId
	 * @参数： @return	
	 * @return： Result
	 */
	public Result setAuthority(String ids,long roleId){
		List<Menu> menus = new ArrayList<Menu>();
		 List<Long> list = Utils.stringToLongList(ids, ",");
		 for(Long id:list){
	        	Menu menu = menuDao.findOne(id);
	        	menus.add(menu);
		 }
		Role role=roleDao.findOne(roleId);
		role.setMenus(menus);
		roleDao.save(role);
		return new Result(1,"保存成功！",null);
    }
	
}
