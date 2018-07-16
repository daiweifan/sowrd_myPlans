package com.daiwei.project.backend.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
 * 文件存储
 * @author david
 *
 * 2017年8月6日 下午8:59:58
 */
@Entity
@Table(name = "s_file")
@Data
@EqualsAndHashCode(callSuper=false)
public class FileInfo {
	
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

	//文件名
	private String name;
	
	//浏览器
	private String browser;
	
	private String  ip;
	
	//存储路径  完整的url 能够直接访问
	private String url;
	
	//后缀名
	private String suffix;
	
	//大小
	private long size;
	
    private Integer type = 0 ;//默认0 本地存储   1 云存储
	
    @ManyToOne
    private User user ;
    
}
