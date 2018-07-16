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
 * 企业基本信息
 * @author david
 *
 */
@Entity
@Table(name = "company")
@Data
public class Company {
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

	//企业名称
	@Column(length=100)
	private String name;
	
	//企业英文名
	@Column(length=100)
	private String englishName;
	
	//缩写
	@Column(length=100)
	private String shortName;
	
	//日本名称
	@Column(length=100)
	private String japanName;
	
	
	//企业介绍   富文本编辑
	@Lob
	private String introduction;
	
	/**简介**/
	@Lob
	private String description;
	
	//座机
	private String telephone;
	
	//公司logo
	@ManyToOne
	private FileInfo logo;
	
	//官微
	private String hibuick;
	
	//微博
	private String weibo;
	
	//企业邮箱
	private String email;
	
	//企业地址
	private String address;
	
	//企业网站地址
	private String website;
	
	//留言提示
	private String messageNotice;
	
	//状态
	private Integer state =11;

	
}
