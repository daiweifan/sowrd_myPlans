package com.daiwei;

import java.util.Map;

import cn.hutool.core.lang.Console;
import cn.hutool.core.map.MapUtil;

public class MapUtilTest {
	public static void main(String[] args) {
		Map<Object, Object> colorMap = MapUtil.of(new String[][] {
		     {"name", "张三"},
		     {"sex", "男"},
		     {"age", "22"},
		     {"name", "李四"},
		     {"sex", "女"},
		     {"age", "21"}
		});
		Console.log("{}",colorMap);
	}
}
