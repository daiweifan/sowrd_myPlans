package com.daiwei;

import java.util.HashMap;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;

public class HttpTest {
	public static void main(String[] args) {
		// 最简单的HTTP请求，可以自动通过header等信息判断编码，不区分HTTP和HTTPS
		String result1= HttpUtil.get("https://www.baidu.com");

/*		// 当无法识别页面编码的时候，可以自定义请求页面的编码
		String result2= HttpUtil.get("https://www.baidu.com", "UTF-8");*/

		//可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("city", "北京");
		String result3= HttpUtil.get("https://www.baidu.com", paramMap);
		Console.log(result3);
	}
}
