package com.daiwei.project.backend.system.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.daiwei.project.backend.system.model.ScheduleJob;



/** 
 * 
* @ClassName: ScheduleJobDao 
* @Description: TODO
* @author daiwei
* @date 2017年12月27日 上午10:58:38 
* 
* @version V2.0
 */
public interface ScheduleJobDao extends JpaRepository<ScheduleJob, Long> {
	
	ScheduleJob findByBeanName(String beanName);
	
	Page<ScheduleJob> findAll(Pageable pageable);
	
	Page<ScheduleJob> findByBeanNameLike(String beanName,Pageable pageable);
	
	
}
