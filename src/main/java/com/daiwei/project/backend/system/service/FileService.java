package com.daiwei.project.backend.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daiwei.project.backend.system.dao.FileDao;
import com.daiwei.project.backend.system.model.FileInfo;
import com.daiwei.utils.Result;

/** 
 * @author  david:
 * @date 创建时间：2017年6月28日 下午9:16:07
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@Service
public class FileService  {
	
	@Autowired
	public FileDao fileDao;

	/**
	 * 保存文件
	* @Description:  
	* @author david  
	* @date 2017年7月21日 下午3:12:53
	 */
	public Result save(FileInfo file){
		fileDao.save(file);
		 return new Result(1,"保存成功！",null);
    }
}
