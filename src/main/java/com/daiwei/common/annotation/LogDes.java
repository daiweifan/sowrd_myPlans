package com.daiwei.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author  david:
 * @date 创建时间：2017年8月29日 下午9:00:36
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogDes {
	
	/**
	 * Value.
	 *
	 * @return the string
	 */
	String value() default "";
}
