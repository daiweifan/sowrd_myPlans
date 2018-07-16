package com.daiwei.project.backend.system.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** * @author  david:
 * @date 创建时间：2016年5月27日 下午4:36:39
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@Entity
@Table(name = "s_user")
@Data
@EqualsAndHashCode(callSuper=false)
@ToString(exclude="roles")
public class User {

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

	
	@Column(unique =true)
	private String username;
	
	//真实姓名
	private String realname;
	
	@Temporal(TemporalType.DATE)
	//出生日期
	private Date birthday;
	
    private Integer sex = 0 ;//默认0 男性
	
	private String password;
	
	@OneToOne
	private FileInfo avatar;
	
	//手机号
	private String phone;
	
	@Column(length=100)
	private String email;
	
	//状态  启用禁用
	private Integer state = 11;
		
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
	        name="s_user_role",
	        joinColumns=@JoinColumn(name="s_user_id", referencedColumnName="id"),
	        inverseJoinColumns=@JoinColumn(name="s_role_id", referencedColumnName="id"))
    private List<Role> roles;// 一个用户具有多个角色

	//获取用户所有的角色
	@Transient
    public Set<String> getRolesName() {
        List<Role> roles = getRoles();
        Set<String> set = new HashSet<String>();
        for (Role role : roles) {
            set.add(role.getName());
        }
        return set;
    }
	
	//获取用户所有的角色
	@Transient
    public Set<String> getRolesCode() {
        List<Role> roles = getRoles();
        Set<String> set = new HashSet<String>();
        for (Role role : roles) {
            set.add(role.getCode());
        }
        return set;
    }
	
}
