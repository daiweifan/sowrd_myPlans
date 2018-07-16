package com.daiwei.common.dto;

import com.daiwei.common.annotation.CondtionExpression;
import com.daiwei.common.annotation.Des;

/**
 *
 * @author  david:
 * @date 创建时间：2017年8月29日 下午7:50:30
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
public class SearchIsdeleteDto extends Dto {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1116620955353163201L;
	
	/** The deleted. */
	@Des("是否删除")
	@CondtionExpression
	public Boolean deleted = false;
	
	/**
	 * Gets the deleted.
	 *
	 * @return the deleted
	 */
	public Boolean getDeleted() {
		return deleted;
	}
	
	/**
	 * Sets the deleted.
	 *
	 * @param deleted the new deleted
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
