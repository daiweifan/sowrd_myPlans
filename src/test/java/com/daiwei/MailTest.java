package com.daiwei;

import java.util.ArrayList;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.mail.MailUtil;

public class MailTest {
	public static void main(String[] args) {
		//MailUtil.send("daiwei_fan@163.com", "测试", "邮件来自Hutool测试", false);
		
		MailUtil.send("daiwei_fan@163.com", "测试", "<h1>邮件来自Hutool测试带附件</h1>", true, FileUtil.file("d:/david/单位版网厅操作手册.docx"));
		
		
		ArrayList<String> tos = CollUtil.newArrayList(
			    "david_inner@163.com", 
			    "daiwei_inner@163.com", 
			    "daiwei_fan@163.com");

		//MailUtil.send(tos, "测试", "邮件来自Hutool群发测试", false);
		
	}
}
