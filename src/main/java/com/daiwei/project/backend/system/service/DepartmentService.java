package com.daiwei.project.backend.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daiwei.project.backend.system.dao.DepartmentDao;
import com.daiwei.project.backend.system.model.Department;
import com.daiwei.utils.Result;
import com.daiwei.utils.Utils;

/**
 * @author  david
 * @date 2017-08-14 14:08:25
 * @version 1.0
 * @parameter
 * @since 
 * @return 
 */
@Service
public class DepartmentService  {
	
	@Autowired
	public DepartmentDao departmentDao;
	
	/**
	 * 保存部门
	* @Description:  
	* @author david  
	* @date 2017-08-14 14:08:25
	 */
	public Result save(Department department){
		departmentDao.save(department);
		return new Result(1,"保存成功！",null);
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
        	Department department = departmentDao.findOne(id);
        	if(departmentDao.findByParent(department)!=null){
        		return Result.error("该部门含有子节点，不能进行删除，只能先删除子部门!");
        	}else{
        		departmentDao.delete(department);
        	}
        }
		return Result.ok("删除成功!");
	}
}
