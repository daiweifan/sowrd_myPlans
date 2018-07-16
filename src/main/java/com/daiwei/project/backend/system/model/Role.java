package com.daiwei.project.backend.system.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;
import lombok.ToString;

/** * @author  david:
 * @date 创建时间：2016年5月27日 下午4:36:39
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@Entity
@Table(name = "s_role")
@Data
@ToString(exclude="menus")
public class Role {
	
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

	//角色的名称
	private String name;
	
	//角色的编码
	private String code;
	   
//	@ManyToMany
//	@JoinTable(
//	        name="s_user_role",
//	        joinColumns=@JoinColumn(name="s_role_id", referencedColumnName="id"),
//	        inverseJoinColumns=@JoinColumn(name="s_user_id", referencedColumnName="id"))
//	private List<User> users;// 一个角色对应多个用户
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
	        name="s_role_menu",
	        joinColumns=@JoinColumn(name="s_role_id", referencedColumnName="id"),
	        inverseJoinColumns=@JoinColumn(name="s_menu_id", referencedColumnName="id"))
	private List<Menu> menus;// 一个角色对应多个菜单


	//获取该角色对应的菜单的权限名称
	@Transient
    public List<String> getPermissionsName() {
        List<String> list = new ArrayList<String>();
        List<Menu> menuList = getMenus();
        for (Menu menu : menuList) {
            list.add(menu.getPermission());
        }
        return list;
    }
	
	@ManyToOne
	private Department department;
	
	//角色的编码
	private Integer sort;
	
	
	//角色的备注
	private String note;
}
