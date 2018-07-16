package com.daiwei;

import cn.hutool.core.lang.Console;

public class ConsoleTest {
	public static void main(String[] args) {
		String[] a = {"abc", "bcd", "def"};
		Console.log(a);//控制台输出：[abc, bcd, def]
		Console.log("This is Console log for {}.", "test");
		//控制台输出：This is Console log for test.
	}
}
