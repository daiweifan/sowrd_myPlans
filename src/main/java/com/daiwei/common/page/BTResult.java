package com.daiwei.common.page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 *
 * @author  david:
 * @date 创建时间：2017年8月30日 下午3:58:24
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@Data
public class BTResult implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 结果集
    private List<Map<String,Object>> rows;

    // 总数
    private long total;
    
    public BTResult(){
    	
    }
    public BTResult(List<Map<String,Object>> rows,long total){
    	this.rows=rows;
    	this.total=total;
    }
}
