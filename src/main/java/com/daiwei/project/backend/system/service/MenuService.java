package com.daiwei.project.backend.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daiwei.common.node.MenuNode;
import com.daiwei.project.backend.system.dao.MenuDao;
import com.daiwei.project.backend.system.model.Menu;
import com.daiwei.utils.Result;
import com.daiwei.utils.Utils;

/** 
 * @author  david:
 * @date 创建时间：2017年6月28日 下午9:16:07
 * @version 1.0
 * @parameter
 * @since 
 * @return 
 */
@Service
public class MenuService  {
	
	@Autowired
	public MenuDao menuDao;
	
	/**
	 * 根据菜单名获取
	 * 方法功能说明
	 * 创建时间 2017年7月20日 下午9:15:39
	 * 开发者 david
	 * @参数： @param name
	 * @参数： @return	
	 * @return： menu
	 */
	public Menu findByName(String name) {
		return menuDao.findByName(name);
	}
	
	/**
	 * 保存菜单
	* @Description:  
	* @author david  
	* @date 2017年7月21日 下午3:12:53
	 */
	public Result save(Menu menu){
		Menu menuTemp = null;
        if(menu.getId() ==null){
        	menuTemp= new Menu();
        }else{
        	menuTemp = menuDao.findOne(menu.getId());
        }
        menuTemp = menu;
        boolean repeat = false;
        Menu menuCheck = null;
        if(menuTemp.getParent()!=null){
        	menuCheck = menuDao.findByCodeAndParent(menuTemp.getCode(), menuTemp.getParent().getId());
        }else{
        	menuCheck = menuDao.findByCode(menuTemp.getCode());
        }
        //判断用户名是否存在
        if(menuCheck!=null){
            if(menuTemp.getId()==null){
                repeat =true;
            }else{
                if(menuTemp.getId()!=menuCheck.getId()){
                    repeat =true;
                }
            }
        }
        if(repeat){
            return new Result(2,"菜单编码[<span style='font-weight:bold;color:red'>"+menu.getCode()+"</span>]已经存在！");
        }else{
        	  menuTemp.setPermission(getPermission(menuTemp));
        	  menuDao.save(menuTemp);
            return new Result(1,"保存成功！",menuTemp);
        }
    }
	
	/**
	 * 根据当前菜单获取权限代码
	 * @param menu
	 * @return
	 */
	public String getPermission(Menu menu){
		StringBuilder sb = new StringBuilder();
		sb.append(menu.getCode());
		Menu parent =menu.getParent();
		while (parent!=null) {
			sb.insert(0, ":");
			sb.insert(0, parent.getCode());
			parent = parent.getParent();
		}
		return sb.toString();
	}
	
	/**
	 * 删除 如果有子节点 不能删除
	 * 方法功能说明
	 * 创建时间 2017年8月10日 下午9:04:02
	 * 开发者 david
	 * @参数： @param ids
	 * @参数： @return	
	 * @return： Result
	 */
    public Result delete(String ids) {
        List<Long> list = Utils.stringToLongList(ids, ",");
        for(Long id:list){
        	Menu menu = menuDao.findOne(id);
        	if(menu.isShowMenu()){
        		return Result.error("菜单含有子节点，不能进行删除，只能先删除子菜单!");
        	}else{
        		menuDao.delete(menu);
        	}
        }
		return Result.ok("删除成功!");
	}
    
    
	/**
	 * 根据菜单名获取  只支持三级菜单
	 * 方法功能说明
	 * 创建时间 2017年7月20日 下午9:15:39
	 * 开发者 david
	 * @参数： @param name
	 * @参数： @return	
	 * @return： menu
	 */
	public List<MenuNode> getMenusByRoleId(Long roleId) {
		List<Menu> menus =  menuDao.getMenusByRoleId(roleId);
		List<MenuNode> menuNodes =  new ArrayList<MenuNode>();
		for(Menu menu:menus){
			//添加一级菜单
			MenuNode menuNode= new MenuNode(menu.getId(),  menu.getName(), menu.getUrl(), menu.getIcon());
			List<Menu> secondMenus =  menuDao.getChildrens(menu.getId(),roleId);
			if(secondMenus.isEmpty()){
				menuNode.setChildrens(new ArrayList<MenuNode>());
			}else{
				//添加二级菜单
				List<MenuNode> secondChildrens =  new ArrayList<MenuNode>();
				for(Menu menu2:secondMenus){
					MenuNode menuNode2= new MenuNode(menu2.getId(), menu2.getName(), menu2.getUrl(), menu2.getIcon());
					List<Menu> thirdMenus =menuDao.getChildrens(menu2.getId(),roleId);
					if(thirdMenus.isEmpty()){
						menuNode2.setChildrens(new ArrayList<MenuNode>());
					}else{
						List<MenuNode> thirdChildrens =  new ArrayList<MenuNode>();
						//添加三级菜单
						for(Menu menu3:thirdMenus){
							MenuNode menuNode3= new MenuNode(menu3.getId(), menu3.getName(), menu3.getUrl(), menu3.getIcon());
							menuNode3.setChildrens(new ArrayList<MenuNode>());
							thirdChildrens.add(menuNode3);
						}
						menuNode2.setChildrens(thirdChildrens);
					}
					secondChildrens.add(menuNode2);
				}
				menuNode.setChildrens(secondChildrens);
			}
			menuNodes.add(menuNode);
		}
		return menuNodes;
	}
	
}
