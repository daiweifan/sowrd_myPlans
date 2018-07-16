package com.daiwei.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识查询bean.
 * @author  david:
 * @date 创建时间：2017年8月28日 下午10:16:37
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SearchBean {

}
