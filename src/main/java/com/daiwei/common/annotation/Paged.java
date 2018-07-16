package com.daiwei.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * searchbean分页参数配置
 * 分页参数.
 * @author  david:
 * @date 创建时间：2017年8月28日 下午10:05:22
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Paged {
	
	/**
	 * 分页参数的名称
	 * @return the string
	 */
	String pageParameName() default "page";
	
	/**
	 * 每页大小的参数名称
	 * @return the string
	 */
	String sizeParameName() default "rows";
	
	/**
	 * 默认页的值
	 * @return the int
	 */
	int defaultPage() default 1;
	
	/**
	 * 默认每页的大小 
	 * @return the int
	 */
	int defaultSize() default 20;
}
