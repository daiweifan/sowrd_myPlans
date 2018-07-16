package com.daiwei.project.backend.system.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.daiwei.project.backend.system.model.LoginLog;

/** 
 * @author  david:
 * @date 创建时间：2017年8月29日 上午10:45:19
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
public interface LoginLogDao extends CrudRepository<LoginLog, Long> {
	

	Page<LoginLog> findAll(Pageable pageable);
	
	Page<LoginLog> findByNameLike(String name,Pageable pageable);
	
}
