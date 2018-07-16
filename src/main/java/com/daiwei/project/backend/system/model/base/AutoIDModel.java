package com.daiwei.project.backend.system.model.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author  david:
 * @date 创建时间：2017年8月1日 下午9:02:49
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
public abstract class AutoIDModel extends Model<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)  
	//"编号"
	protected Long id;
	
	/* (non-Javadoc)
	 * 
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}	

}
