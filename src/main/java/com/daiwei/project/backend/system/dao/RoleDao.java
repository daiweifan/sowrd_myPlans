package com.daiwei.project.backend.system.dao;

import org.springframework.data.repository.CrudRepository;

import com.daiwei.project.backend.system.model.Role;

/** * @author  david:
 * @date 创建时间：2016年5月30日 上午10:45:19
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
public interface RoleDao extends CrudRepository<Role, Long> {
	

	Role findByName(String name);
	
}
