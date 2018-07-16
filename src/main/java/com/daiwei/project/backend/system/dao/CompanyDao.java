package com.daiwei.project.backend.system.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.daiwei.project.backend.system.model.Company;



/** 
 * 企业的dao
 * @author david
 *
 * 2017年7月31日 下午8:44:05
 */
public interface CompanyDao extends JpaRepository<Company, Long> {
	
	Company findByName(String name);
	
	Page<Company> findAll(Pageable pageable);
	
	Page<Company> findAll(Specification<Company> spec,Pageable pageable);
	
	Page<Company> findByNameLike(String name,Pageable pageable);
	
	@Modifying
	@Query("update Company t set t.deleted =1 where t.id in :ids") 
	@Transactional
	void deletedByIds(@Param("ids") List<Long> ids);
	
	
	@Modifying
	@Query("update Company t set t.state =:actived where t.id in :ids") 
	@Transactional
	void changeStateByIds(@Param("ids") List<Long> ids,@Param("actived")boolean actived);
	
}
