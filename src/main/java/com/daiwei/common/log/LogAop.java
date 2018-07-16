package com.daiwei.common.log;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义log注解 注入了该注解则去生成日志
 * @author david
 *
 */
@Retention(RetentionPolicy.RUNTIME)//注解会在class中存在，运行时可通过反射获取
@Target(ElementType.METHOD)//目标是方法
@Documented//文档生成时，该注解将被包含在javadoc中，可去掉
public @interface LogAop {
    String action() default ""; //操作类型  1，  登录,2，新增 3，修改 4，查询 5，删除 6,上传文件
    String targetType() default ""; //处理的那个实体类
    String remark() default "";
}
