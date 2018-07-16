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

/**
 * 菜单表
 * @author david
 *
 * 2017年7月13日 下午10:33:03
 */
@Entity
@Table(name = "s_menu")
@Data
public class Menu {
	
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

	//菜单名称
	private String name;
	
	//菜单编码
	private String code;
	
	//菜单路径
	private String url;
	
	//父节点
	@ManyToOne
	private Menu parent;
	
	//排序号
	private Integer sort;
	
	//icon图标
	private String icon;
	
	//是否展示为菜单
	private boolean showMenu =false;
	
	//状态
	private int state =11;
	
	//权限缩写（自动生成 父节点编码：自己的编码）
	private String permission;
	
	
/*	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
	        name="s_role_menu",
	        joinColumns=@JoinColumn(name="s_menu_id", referencedColumnName="id"),
	        inverseJoinColumns=@JoinColumn(name="s_role_id", referencedColumnName="id"))
    private List<Role> roles;// 一个用户具有多个角色
*/
	
}
