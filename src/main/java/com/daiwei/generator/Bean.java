package com.daiwei.generator;

import lombok.Data;

/**
 * 创建参数储存实体类
 * @author david
 *
 */
@Data
public class Bean {

	/** bean class名称 */
	private String name;
	/** bean 首字母小写名称 */
	private String lowerName;
	/**含义**/
	private String content;
	/** bean 路径 */
	private String packageUrl;
	/**实体类映射的table */
	private String tableName;
	/**
	 * 作者名称
	 */
	private String authorName;
	/**
	 * 作者邮箱
	 */
	private String authorMail;
	/**
	 * 日期
	 */
	private String date;
	/**
	 * 版本
	 */
	private String version;


}