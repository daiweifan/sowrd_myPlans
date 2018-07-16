package com.daiwei.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.persistence.criteria.JoinType;

import com.daiwei.common.dto.CondtionType;

/**
 * searchbean的查询条件.
 * @author  david:
 * @date 创建时间：2017年8月28日 下午10:10:18
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CondtionExpression {
	//对应的bean的字段如果为"" 默认当前字段名
	/**
	 * 默认当前参数名称，如果bean字段名称和当前参数名称不一样可以设置
	 * @return the string
	 */
	String value() default "";
	
	/**
	 * 默认当前单数的类型，如果参数类型不一样可以设置类型
	 * @return the class
	 */
	Class<?> prameType() default Object.class;
	
	/**
	 * 查询的类型 支持 等于、不等于、为空、不为空、in、like、大于等于、小于等于、大于、小于等
	 * 具体请看 CondtionType枚举中的值
	 * @return the condtion type
	 */
	CondtionType type() default CondtionType.equal;	
	
	/**
	 * 联表查询参数
	 * @return the string
	 */
	String joinName() default "";
	
	/**
	 * 联表查询参数类
	 * @return the join type
	 */
	JoinType joinType() default JoinType.INNER;
	
	/**
	 * 遇到值为日期格式的format规格
	 * @return the string
	 */
	String dateFormatter() default "yyyy-MM-dd";
	
	/**
	 * 查询结果取 true 还是false
	 * @return true
	 */
	boolean result() default true;
	
	/**
	 * 使用的函数
	 * @return
	 */
	Function function() default @Function();
}
