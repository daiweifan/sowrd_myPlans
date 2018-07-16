package com.daiwei;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.lang.Console;

public class FileReaderTest {
	public static void main(String[] args) {
		//默认UTF-8编码，可以在构造中传入第二个参数做为编码
		FileReader fileReader = new FileReader("application.properties");
		String result = fileReader.readString();
		Console.log(result);
	}
}
