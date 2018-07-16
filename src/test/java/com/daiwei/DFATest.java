package com.daiwei;

import java.util.List;

import org.junit.Assert;

import cn.hutool.core.lang.Console;
import cn.hutool.dfa.WordTree;

public class DFATest {
	public static void main(String[] args) {
		WordTree tree = new WordTree();
		tree.addWord("大");
		tree.addWord("大土豆");
		tree.addWord("土豆");
		tree.addWord("刚出锅");
		tree.addWord("出锅");
		
		String text = "我有一颗大土豆，刚出锅的";
		
		// 匹配到【大】，就不再继续匹配了，因此【大土豆】不匹配
		// 匹配到【刚出锅】，就跳过这三个字了，因此【出锅】不匹配（由于刚首先被匹配，因此长的被匹配，最短匹配只针对第一个字相同选最短）
		List<String> matchAll = tree.matchAll(text, -1, false, false);
		Assert.assertEquals(matchAll.toString(), "[大, 土豆, 刚出锅]");
	}
}
