package com.daiwei.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author  david:
 * @date 创建时间：2017年7月22日 下午9:45:37
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
public class Utils {

	/**
	 * 功能：将字符串转化成Integer类型list
	 * @param str  需要处理的字符串
	 * @param splitStr  分割字符  例如：,  |
	 * @return list 
	 */
	public static List<Integer> stringToIntegerList(String str,String splitStr){
		List<Integer> list = new ArrayList<Integer>();
		if(str.isEmpty()){
			return list;
		}
		String[] array = str.split(splitStr);
		for(String id : array){
			list.add(Integer.parseInt(id));
		}
		return list;
	}
	
	
	/**
	 * 功能：将字符串转化成Integer类型list
	 * @param str  需要处理的字符串
	 * @param splitStr  分割字符  例如：,  |
	 * @return list 
	 */
	public static List<Long> stringToLongList(String str,String splitStr){
		List<Long> list = new ArrayList<Long>();
		if(str.isEmpty()){
			return list;
		}
		String[] array = str.split(splitStr);
		for(String id : array){
			list.add(Long.parseLong(id));
		}
		return list;
	}
	
	
		//首字母转小写
	    public static String toLowerCaseFirstOne(String s)
	    {
	        if(Character.isLowerCase(s.charAt(0)))
	            return s;
	        else
	            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	    }
	    //首字母转大写
	    public static String toUpperCaseFirstOne(String s)
	    {
	        if(Character.isUpperCase(s.charAt(0)))
	            return s;
	        else
	            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	    }
	    
	    public static String convertSfName(boolean value){
	    	if(value){
	    		return "是";
	    	}else{
	    		return "不是";
	    	}
	    }
}
