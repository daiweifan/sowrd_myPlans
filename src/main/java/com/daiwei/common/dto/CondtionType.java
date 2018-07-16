package com.daiwei.common.dto;
/**
 *
 * @author  david:
 * @date 创建时间：2017年8月28日 下午10:09:13
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
public enum CondtionType {
	//ge(">="),gt(">"),le("<="),lt("<"),eq("="),ne("!="),like("like"),is("is"),in("in");
		/** The equal. */
		equal ,
		
		/** The notequal. */
		notequal ,
		
		/** The like. */
		like ,
		
		/** The greaterthan. */
		greaterthan,
		
		/** The greaterthan orequal. */
		greaterthanOrequal,
		
		/** The lessthan. */
		lessthan,
		
		/** The lessthan orequal. */
		lessthanOrequal,
		
		/** The isnullorempty. */
		isnullorempty,
		
		/** The isnull. */
		isnull,
		
		/** The isnotnull. */
		isnotnull,
		
		/** The isempty. */
		isempty,
		
		/** The in. */
		in;
}
