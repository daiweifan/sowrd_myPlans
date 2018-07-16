package ${bean.packageUrl}.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${bean.packageUrl}.dao.${bean.name}Dao;
import ${bean.packageUrl}.model.${bean.name};
import com.daiwei.utils.Result;

/**
 * @author  ${bean.authorName}
 * @date ${bean.date}
 * @version ${bean.version}
 * @parameter
 * @since 
 * @return 
 */
@Service
public class ${bean.name}Service  {
	
	@Autowired
	public ${bean.name}Dao ${bean.lowerName}Dao;
	
	/**
	 * 保存${bean.content}
	* @Description:  
	* @author ${bean.authorName}  
	* @date ${bean.date}
	 */
	public Result save(${bean.name} ${bean.lowerName}){
		${bean.lowerName}Dao.save(${bean.lowerName});
		return new Result(1,"保存成功！",null);
    }
}
