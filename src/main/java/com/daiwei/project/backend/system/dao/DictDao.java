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

import com.daiwei.project.backend.system.model.Dict;
import com.daiwei.project.backend.system.model.Menu;



/**
 * 
 * @author david
 *
 * 2017年8月6日 下午9:20:49
 */
public interface DictDao extends JpaRepository<Dict, Long> {
	

	Dict findByName(String name);
	
	Dict findByCode(String code);
	
	
	Page<Dict> findAll(Pageable pageable);
	
	Page<Dict> findAll(Specification<Dict> spec,Pageable pageable);
	

	@Query("select t from Dict t where t.parent.id= 0 or t.parent is null ")
	List<Dict> findAllParent();
	
	@Query("select t from Dict t where t.code=:code and t.parent.id =:parentId")
	Dict findByCodeAndParent(@Param("code")String code,@Param("parentId")long parentId);
	
	@Modifying
	@Query("update Dict t set t.deleted =1 where t.id in :ids") 
	@Transactional
	void deletedByIds(@Param("ids") List<Long> ids);
}
