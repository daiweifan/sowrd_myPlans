package com.daiwei;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class Out {
	   public Out(){    
	        try {    
	             String directory ="D:/";
	        	 String fileName = "print_log.txt";
	             // 在内存中创建一个文件对象，注意：此时还没有在硬盘对应目录下创建实实在在的文件
	             File f = new File(directory,fileName);
	             if(f.exists()) {
	               // 文件已经存在，输出文件的相关信息
	                 System.out.println(f.getAbsolutePath());
	                 System.out.println(f.getName());
	                 System.out.println(f.length());
	             } else {
	               //  先创建文件所在的目录
	                 f.getParentFile().mkdirs();
	                 try {
	                  // 创建新文件
	                     f.createNewFile();
	                 } catch (IOException e) {
	                     System.out.println("创建新文件时出现了错误。。。");
	                     e.printStackTrace();
	                 }
	             }
	            PrintStream print=new PrintStream("D:\\print_log.txt");  //写好输出位置文件；  
	            System.setOut(print);    
	        } catch (FileNotFoundException e) {    
	            e.printStackTrace();    
	        }    
	    }  
}
