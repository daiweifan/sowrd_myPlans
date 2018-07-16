package com.daiwei.project.backend.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.daiwei.project.backend.system.model.Menu;



/**
 * 
 * @author david
 *
 * 2017年8月6日 下午9:20:49
 */
public interface MenuDao extends JpaRepository<Menu, Long> {
	

	Menu findByName(String name);
	
	Menu findByCode(String code);
	
	//根据父节点和code获取菜单 
//	@Query(value="select * from s_menu  where code=:code and parent =:parentId and  state =11",nativeQuery=true)
	@Query("select t from Menu t where t.code=:code and t.parent.id =:parentId and  t.state =11")
	Menu findByCodeAndParent(@Param("code")String code,@Param("parentId")long parentId);
	
	
	//获取所有是菜单的父节点 
	@Query("select t from Menu t where t.showMenu = true and t.state =11")
	List<Menu> findAllParent();
	
	//获取所有有效的菜单
	@Query("select t from Menu t where t.state =11")
	List<Menu> findAllByState();
	
	//根据角色获取所有有效的一级菜单
	@Query(value="select *  from  s_menu a  where  show_menu =1 and a.id in (select  s_menu_id from s_role_menu where s_role_id=:roleId) and a.parent is  null order by sort",nativeQuery=true)
	List<Menu> getMenusByRoleId(@Param("roleId") long id);
	
	//获取下级菜单（左侧展示）
	@Query(value="select * from s_menu where parent =:id and show_menu=1 and id in (select  s_menu_id from s_role_menu where s_role_id=:roleId)",nativeQuery=true)
	List<Menu> getChildrens(@Param("id") long id,@Param("roleId") long roleId);

}
