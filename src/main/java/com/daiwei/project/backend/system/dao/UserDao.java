package com.daiwei.project.backend.system.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.daiwei.project.backend.system.model.User;



/** * @author  david:
 * @date 创建时间：2016年5月30日 上午10:45:19
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
public interface UserDao extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	
	User findById(long id);
	
	void deleteById(long id);
	
	Page<User> findAll(Pageable pageable);
	
	Page<User> findByUsernameLike(String username,Pageable pageable);
}
