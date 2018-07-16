package com.daiwei;

import lombok.Data;

@Data
public class Param {

	private String code;
	private String msg;
	
	public Param(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
}
