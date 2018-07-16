package com.daiwei;

import java.io.IOException;
import java.util.Properties;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.Console;

public class ClassPathTest {
	public static void main(String[] args) {
		ClassPathResource resource = new ClassPathResource("application.properties");
		Properties properties = new Properties();
		try {
			properties.load(resource.getStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Console.log("Properties: {}", properties);
	}
}
