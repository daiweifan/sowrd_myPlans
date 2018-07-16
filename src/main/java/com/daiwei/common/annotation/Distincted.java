package com.daiwei.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * searchbean分页参数配置
 * 分页参数.
 * @author  david:
 * @date 创建时间：2017年8月28日 下午10:08:07
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Distincted {
	
	/**
	 * Value.
	 *
	 * @return true, if successful
	 */
	boolean value() default true;
}
