package com.daiwei.project.backend.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.daiwei.common.page.SortBuilder;
import com.daiwei.project.backend.system.dao.ScheduleJobDao;
import com.daiwei.project.backend.system.model.ScheduleJob;
import com.daiwei.utils.StringKit;

/** 
 * @author  david:
 * @date 创建时间：2017年12月27日 下午9:16:07
 * @version 2.0
 * @parameter
 * @since 
 * @return 
 */
@Service
public class ScheduleJobService  {
	
	@Autowired
	public ScheduleJobDao scheduleJobDao;


	
	public ScheduleJob findById(Long id) {
		return scheduleJobDao.findOne(id);
	}
	
	
	/**
	 * 
	* @Title: getscheduleJobPageByName 
	* @Description: 根据bean名称查询
	* @param @param name
	* @param @param page
	* @param @param size
	* @param @return    设定文件 
	* @return Page<ScheduleJob>    返回类型 
	* @throws
	 */
	public Page<ScheduleJob> getscheduleJobPageByName(String name,Integer page,Integer size ) {
		Pageable pageable = new PageRequest(page,size,SortBuilder.generateSort("createTime desc","id asc"));
		if(StringKit.isEmpty(name)){
			return scheduleJobDao.findAll(pageable);
		}else{
			return scheduleJobDao.findByBeanNameLike("%"+name+"%",pageable);
		}
	}
	
	/**
	* 
	* @Title: save 
	* @Description: 
	* @param @param scheduleJob
	* @param @return    设定文件 
	* @return ScheduleJob    返回类型 
	* @throws
	 */
	public ScheduleJob save(ScheduleJob scheduleJob){
		return scheduleJobDao.save(scheduleJob);
    }
	
	
	public void delete( long id) {
	    scheduleJobDao.delete(id);
	}
}
