package com.daiwei.project.backend.system.dto;

import com.daiwei.common.annotation.CondtionExpression;
import com.daiwei.common.annotation.Des;
import com.daiwei.common.annotation.Distincted;
import com.daiwei.common.annotation.Paged;
import com.daiwei.common.annotation.SearchBean;
import com.daiwei.common.annotation.Sorted;
import com.daiwei.common.dto.CondtionType;
import com.daiwei.common.dto.SearchIsdeleteDto;

/**
 *  
*类描述：收入记录Dto
*@author: david
*@date： 日期：2016年6月25日 时间：上午8:32:24
*@version 1.0
 */
@SearchBean
@Paged
@Distincted
@Sorted(value={"createTime desc"})
public class CompanyDto extends SearchIsdeleteDto {
	private static final long serialVersionUID = 1L;
	
	 @Des("企业名称")
	 @CondtionExpression(value="name",type = CondtionType.like)
	 private String name;
	  
    @Des("结束时间")
    @CondtionExpression(value="createTime",type = CondtionType.lessthan)
    private String endTime;

    @Des("开始时间")
    @CondtionExpression(value="createTime",type = CondtionType.greaterthanOrequal)
    private String startTime;
}
