package com.daiwei.project.backend.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daiwei.project.backend.system.dao.OperationLogDao;
import com.daiwei.project.backend.system.model.OperationLog;
import com.daiwei.utils.Result;
import com.daiwei.utils.Utils;

/**
 * @author  david
 * @date 2017-09-02 14:08:25
 * @version 1.0
 * @parameter
 * @since 
 * @return 
 */
@Service
public class OperationLogService  {
	
	@Autowired
	public OperationLogDao operationLogDao;
	
	/**
	 * 保存
	* @Description:  
	* @author david  
	* @date 2017-08-14 14:08:25
	 */
	public Result save(OperationLog operationLog){
		operationLogDao.save(operationLog);
		return new Result(1,"保存成功！",null);
    }
	
	/**
	 * 删除 
	 * 方法功能说明
	 * 创建时间 2017年8月10日 下午9:04:02
	 * 开发者 david
	 * @参数： @param ids
	 * @参数： @return	
	 * @return： Result
	 */
    public Result delete(String ids) {
        List<Long> list = Utils.stringToLongList(ids, ",");
        for(Long id:list){
        	OperationLog operationLog = operationLogDao.findOne(id);
            operationLogDao.delete(operationLog);
        }
		return Result.ok("删除成功!");
	}
}
