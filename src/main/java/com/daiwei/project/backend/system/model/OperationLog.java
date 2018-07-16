package com.daiwei.project.backend.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/** 
 * 登录日志
 * @author  david:
 * @date 创建时间：2017年8月29日 下午4:36:39
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@Entity
@Table(name = "s_operation_log")
@Data
public class OperationLog {
	
	//-----------通用字段---------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long id;
	
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	//创建时间
	protected Date createTime;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	//上一次更新时间
	protected Date updateTime;

	//更新次数
	protected Integer version=0;
	
	//是否删除
	protected Boolean deleted=false;
	
	@PrePersist
	private void onCreate() {
		createTime = updateTime = new Date();
	}

	@PreUpdate
	private void onUpdate() {
		updateTime = new Date();
		version =version + 1;
	}
	//-----------通用字段---------

	//日志的名称
	private String name;
	
	//日志的类型
	private String type;
	
	//类名
	private String className;
	
	//类名
	private String ip;
	
	//方法名
	private String methodName;
	
	//参数
	@Lob
	private String params;
	
	//反应时间
	private long resTime;
	
	//是否成功
	private String succeed;
	
	@ManyToOne
	private User user;
	
	//返回信息
	@Lob
	private String message; //可能是错误日志 可能是修改对比内容
}
