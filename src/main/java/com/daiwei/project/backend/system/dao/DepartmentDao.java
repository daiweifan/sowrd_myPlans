package com.daiwei.project.backend.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.daiwei.common.node.ZTreeNode;
import com.daiwei.project.backend.system.model.Department;




/** 
 * 部门的dao
 * @author david
 *
 * 2017-08-14 14:08:25
 */
public interface DepartmentDao extends CrudRepository<Department, Long> {
	
	Department findByName(String name);
	
	Department findByParent(Department parent);
	
	@Query("select t from  Department t where t.parent =:parent and t.state=11")
	Department findByParentId(@Param("parent") long parent);
	
	Department findByCode(String code);
	
	@Query("select t from Department t where t.code=:code and t.parent.id =:parentId and  t.state =11")
	Department findByCodeAndParent(@Param("code")String code,@Param("parentId")long parentId);
	
	@Query("select t from  Department t where t.state=11")
	List<Department> departmentTreeList();
	
	//获取所有节点
	@Query(value="select id,parent as pId,name,(CASE (parent !=null) WHEN 1 THEN 'true' ELSE 'false' END ) as open,'true' as checked from s_department ",nativeQuery=true)
	List<ZTreeNode> departmentTreeList1();
}
