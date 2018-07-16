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
import com.daiwei.project.backend.system.model.Department;
import com.daiwei.project.backend.system.service.DepartmentService;
import com.daiwei.utils.BeanKit;
import com.daiwei.utils.Result;

/** 
 * @author  david(daiwei_fan@163.com)
 * @date 创建时间：2017-08-14 14:08:25
 * @version 1.0
 * @parameter
 * @since 
 * @return 
 */
@RestController
@RequestMapping("/backend/system/department")
public class DepartmentController extends BaseController {
	
	public static final String viewPath = "backend/system/department/";
	
	@Autowired
	private DepartmentService departmentService;
	/**
	 * 展示部门
	 * 方法功能说明
	 * 创建时间 2017-08-14 14:08:25
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
	 * 部门首页
	* @Description:  
	* @author david 
	* @date 2017-08-14 14:08:25
	 */
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewPath+"index");
		return mav;
	}

	/**
	 * 新增部门
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
	 * 修改部门信息
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:55:54
	 * 开发者david 
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable long id){
		ModelAndView mav = new ModelAndView();
		Department department = departmentService.departmentDao.findOne(id);
		mav.addObject("department", department);
		mav.addObject("parentName", department.getParent()==null?null:department.getParent().getName());
		mav.addObject("parentId", department.getParent()==null?null:department.getParent().getId());
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
	@RequestMapping("/list")
    @ResponseBody
	public List<Map<String,Object>> list(@RequestParam Map<String,Object> map,@RequestParam(required = false) String name, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer deptid){
		List<Map<String,Object>> list = new ArrayList<>();
		
		List<Department> departmentList = (List<Department>) departmentService.departmentDao.findAll();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Department department:departmentList){
			Map<String,Object> mapTemp = BeanKit.describe(department,"parent");
			mapTemp.put("createTime", sdf.format(department.getCreateTime()));
			mapTemp.put("parentName", department.getParent()==null?null:department.getParent().getName());
			mapTemp.put("parentId", department.getParent()==null?null:department.getParent().getId());
			mapTemp.put("parentCode", department.getParent()==null?null:department.getParent().getCode());
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
		return departmentService.delete(ids);
	}
	
	@ResponseBody
    @RequestMapping(value="/save")
    public Result save(Department department){
        return  departmentService.save(department);	
    }
	
	
	/**
	 * 获取所有上级部门
	 * 方法功能说明
	 * 创建时间 2017年8月14日 下午9:26:37
	 * 开发者 david
	 * @参数： @param map
	 * @参数： @return	
	 * @return： List<ZTreeNode>
	 */
	@RequestMapping("/selectDepartmentTreeList")
    @ResponseBody
	public List<ZTreeNode> selectDepartmentTreeList(@RequestParam Map<String,Object> map){
		List<ZTreeNode> list = new ArrayList<ZTreeNode>();
		List<Department> departmentList = departmentService.departmentDao.departmentTreeList();
	     for(Department department:departmentList){
	    	 list.add(new ZTreeNode(department.getId(),department.getParent()==null? 0l:department.getParent().getId(),department.getName(),true,true));
	     }
	     return list;
	}
}
