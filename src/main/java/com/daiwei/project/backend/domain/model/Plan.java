package com.daiwei.project.backend.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * 任务实体类
* @ClassName: Plan 
* @Description: TODO
* @author daiwei
* @date 2018年7月16日 下午4:10:06 
* 
* @version V2.0
 */
@Entity
@Table(name = "plan")
@Data
public class Plan {
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

	//任务名称
	@Column(length=10)
	private String name;
	
	/**详情**/
	@Lob
	private String detail;
	
	//完成度
	private int complete;
	
	//截止时间
	@Temporal(TemporalType.DATE)
	protected Date deadline;
	
	//备注
	private String note;
	
	//优先级
	private Integer level =0; //0.普通  1 ， 紧急  ，2立刻
	
	//状态
	private Integer state =0; //0.进行中  1 ， 完成  ，99  取消

	
}
