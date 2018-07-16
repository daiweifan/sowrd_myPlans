package com.daiwei.project.backend.system.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 *
 * @author  david:
 * @date 创建时间：2017年8月1日 下午8:57:22
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
public abstract class Model<ID> extends BaseEntity<ID> {
	/** The Constant serialVersionUID. */
	@Transient
	// 不被序列化
	private static final long serialVersionUID = 4356379855403575219L;

	/** The create time. */
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	//"创建时间"
	protected Date createTime;

	/** The update time. */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	//"上一次更新时间"
	protected Date updateTime;

	/** The version. */
	@Version
	//"更新次数"
	protected Integer version;

	/**
	 * Sets the creates the time.
	 * 
	 * @param createTime
	 *            the new creates the time
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * Gets the update time.
	 * 
	 * @return the update time
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * Sets the update time.
	 * 
	 * @param updateTime
	 *            the new update time
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * Gets the version.
	 * 
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 * 
	 * @param version
	 *            the new version
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * On create.
	 */
	@PrePersist
	protected void onCreate() {
		createTime = updateTime = new Date();
		deleted = false;
	}

	/**
	 * On update.
	 */
	@PreUpdate
	protected void onUpdate() {
		updateTime = new Date();
	}
}
