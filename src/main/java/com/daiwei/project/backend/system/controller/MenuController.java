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

import com.daiwei.common.log.LogAop;
import com.daiwei.common.node.ZTreeNode;
import com.daiwei.project.backend.system.model.Menu;
import com.daiwei.project.backend.system.model.Role;
import com.daiwei.project.backend.system.service.MenuService;
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
@RequestMapping("/backend/system/menu")
public class MenuController extends BaseController {
	
	public static final String viewPath = "backend/system/menu/";
	
	
	@Autowired
	private MenuService menuService;
	
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
		Menu menu = menuService.menuDao.findOne(id);
		mav.addObject("showMenu", menu.isShowMenu()?1:0);
		mav.addObject("menu", menu);
		mav.addObject("parentName", menu.getParent()==null?null:menu.getParent().getName());
		mav.addObject("parentId", menu.getParent()==null?null:menu.getParent().getId());
		mav.setViewName(viewPath+"edit");
		return mav;
	}
	
	/**
	 * 企业列表
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:55:42
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	//@LogAop(action="查询",remark="菜单列表查询",targetType="menu")
	@RequestMapping("/list")
    @ResponseBody
	public List<Map<String,Object>> list(@RequestParam Map<String,Object> map,@RequestParam(required = false) String name, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer deptid){
		List<Map<String,Object>> list = new ArrayList<>();
		
		List<Menu> menuList = (List<Menu>) menuService.menuDao.findAll();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Menu menu:menuList){
			Map<String,Object> mapTemp = BeanKit.describe(menu,"roles","parent");
			mapTemp.put("createTime", sdf.format(menu.getCreateTime()));
			mapTemp.put("isMenuName", Utils.convertSfName(menu.isShowMenu()));
			mapTemp.put("parentCode", menu.getParent()!=null ? menu.getParent().getCode():"");
			mapTemp.put("parentName", menu.getParent()!=null ? menu.getParent().getName():"");
			list.add(mapTemp);
		}
		return list;
	}
	
	/**
	 * 获取所有菜单
	 * 方法功能说明
	 * 创建时间 2017年8月7日 下午10:15:23
	 * 开发者 david
	 * @参数： @param map
	 * @参数： @return	
	 * @return： List<Map<String,Object>>
	 */
	@RequestMapping("/selectMenuTreeList")
    @ResponseBody
	public List<ZTreeNode> selectMenuTreeList(@RequestParam Map<String,Object> map){
		List<ZTreeNode> list = new ArrayList<ZTreeNode>();
	     List<Menu> menuList = menuService.menuDao.findAllParent();
	     list.add(ZTreeNode.createParent());
	     for(Menu menu:menuList){
	    	 list.add(new ZTreeNode(menu.getId(),menu.getParent()==null? 0l:menu.getParent().getId(),menu.getName(),menu.isShowMenu(),true));
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
	@LogAop(action="删除",remark="删除菜单",targetType="menu")
	@ResponseBody
	@RequestMapping(value = "/delete")
    public Result delete(String ids) {
		return menuService.delete(ids);
	}
	
	
	/**
	 * 新增
	 * 方法功能说明
	 * 创建时间 2017年8月6日 下午9:28:43
	 * 开发者 david
	 * @参数： @param menu
	 * @参数： @return	
	 * @return： Result
	 */
	@LogAop(action="新增",remark="新增菜单",targetType="menu")
	@ResponseBody
    @RequestMapping(value="/save")
    public Result save(Menu menu){
        return  menuService.save(menu);	
    }
	
	/**
	 * 修改
	 * 方法功能说明
	 * 创建时间 2017年8月6日 下午9:28:43
	 * 开发者 david
	 * @参数： @param menu
	 * @参数： @return	
	 * @return： Result
	 */
	@LogAop(action="修改",remark="修改菜单",targetType="menu")
	@ResponseBody
    @RequestMapping(value="/update")
    public Result update(Menu menu){
        return  menuService.save(menu);	
    }
	
	
	/**
	 * 获取所有菜单
	 * 方法功能说明
	 * 创建时间 2017年8月7日 下午10:15:23
	 * 开发者 david
	 * @参数： @param map
	 * @参数： @return	
	 * @return： List<Map<String,Object>>
	 */
	@RequestMapping("/selectMenuTreeListByRoleId/{id}")
    @ResponseBody
	public List<ZTreeNode> selectMenuTreeListByRoleId(@PathVariable long id){
		 List<ZTreeNode> list = new ArrayList<ZTreeNode>();
	//	 list.add(ZTreeNode.createUncheckParent());
		 Role role = roleService.roleDao.findOne(id);
		 List<Menu> menus = role.getMenus();
		 List<Menu> menuList = menuService.menuDao.findAllByState();
		 for(Menu menu:menuList){
			 if(menus.contains(menu)){
		    	 list.add(new ZTreeNode(menu.getId(),menu.getParent()==null? 0l:menu.getParent().getId(),menu.getName(),menu.isShowMenu(),true));
			 }else{
		    	 list.add(new ZTreeNode(menu.getId(),menu.getParent()==null? 0l:menu.getParent().getId(),menu.getName(),menu.isShowMenu(),false));
			 }

	     }
	  
	     return list;
	}
}
