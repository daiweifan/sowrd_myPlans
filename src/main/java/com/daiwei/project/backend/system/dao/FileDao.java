package com.daiwei.project.backend.system.dao;

import org.springframework.data.repository.CrudRepository;

import com.daiwei.project.backend.system.model.FileInfo;



/** 
 * 
 * @author david
 *
 * 2017年8月23日 下午10:38:20
 */
public interface FileDao extends CrudRepository<FileInfo, Long> {
	

}
