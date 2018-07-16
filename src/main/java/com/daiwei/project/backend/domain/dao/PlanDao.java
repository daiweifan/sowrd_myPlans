package com.daiwei.project.backend.domain.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.daiwei.project.backend.domain.model.Plan;



/** 
 * 计划
* @ClassName: PlanDao 
* @Description: TODO
* @author daiwei
* @date 2018年7月16日 下午4:11:20 
* 
* @version V2.0
 */
public interface PlanDao extends JpaRepository<Plan, Long> {
	
	
	Page<Plan> findAll(Pageable pageable);
	
	Page<Plan> findAll(Specification<Plan> spec,Pageable pageable);
	
	
	@Modifying
	@Query("update Plan t set t.deleted =1 where t.id in :ids") 
	@Transactional
	void deletedByIds(@Param("ids") List<Long> ids);
	
	

}
