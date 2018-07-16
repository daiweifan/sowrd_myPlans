package com.daiwei.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 *
 * @author  david:
 * @date 创建时间：2017年8月28日 下午10:14:35
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@Target(value=ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Function {
	String value() default "";
	Class<?> type() default Object.class;
	Argument[] args() default {@Argument()};
			
	
	@Target(value=ElementType.ANNOTATION_TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Argument{
		String value() default "";
		Class<?> type() default Object.class;
		String target() default "";
	}
}
