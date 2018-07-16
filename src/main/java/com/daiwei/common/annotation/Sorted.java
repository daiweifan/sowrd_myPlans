package com.daiwei.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 查询bean的排序.
 * @author  david:
 * @date 创建时间：2017年8月28日 下午10:06:50
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sorted {
	
	/**
	 * 排序 允许多个
	 * @return the string[]
	 */
	public String[] value() default {"updateTime desc"};
	
	/**
	 * 参数名称 
	 * @return the string
	 */
	public  String sortPrameName() default "sort";
	
	/**
	 * 排序类型参数名称[desc还是asc]
	 * @return the string
	 */
	public  String orderPrameName() default "order";	
	
	/**
	 * 是否只用 prame进行排序.
	 * @return true, if successful
	 */
	public  boolean onlyPrameSort() default false;
}
