package com.daiwei.utils;


import java.io.Serializable;
import java.util.Map;

import lombok.Data;

/**
 * 
* @ClassName: Result 
* @Description: 用于返回参数
* @author daiwei
* @date 2017年7月6日 下午5:28:56 
* 
* @version V2.0
 */
@Data
public class Result implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7419113236296271021L;
	private int code;//返回的编码  1 是成功  2是失败
	private String msg;//返回信息
	private Object data;//返回的数据源
	
	
	//构造方法
	public Result() {
		
	}
	
	public Result(int code,String msg) {
		this.code=code;
		this.msg=msg;
	}
	
	public Result(int code,String msg,Object data) {
		this.code=code;
		this.msg=msg;
		this.data=data;
	}
	
	public static Result error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static Result error(String msg) {
		return error(500, msg);
	}
	
	public static Result error(int code, String msg) {
		return new Result(code,msg);
	}

	public static Result ok(String msg) {
		return new Result(1,msg);
	}
	
	public static Result ok(String msg,Map<String, Object> map) {
		return new Result(1,msg,map);
	}
	
	public static Result ok() {
		return new Result(1,"返回成功！");
	}

	
 }
