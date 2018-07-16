package com.daiwei;

import java.util.List;

import org.junit.Assert;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.dfa.WordTree;

public class RuntimeTest {
	public static void main(String[] args) {
		String str = RuntimeUtil.execForStr("ipconfig");
		Console.log(str);
		Console.log(RuntimeUtil.execForLines("java -version"));
	}
}
