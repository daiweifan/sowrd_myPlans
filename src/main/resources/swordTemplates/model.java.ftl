package ${bean.packageUrl}.model;

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
import lombok.EqualsAndHashCode;

/**
 * ${bean.content}基本信息
 * @author ${bean.authorName}
 *
 */
@Entity
@Table(name = "${bean.tableName}")
@Data
public class ${bean.name} {
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

	

	
}
