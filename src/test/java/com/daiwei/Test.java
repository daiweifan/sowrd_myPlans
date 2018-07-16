package com.daiwei;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Test {

	
	
	public static void main(String[] args) {
		
		
		System.out.println("isnotblank:"+StringUtils.isNotBlank(null));
		System.out.println("isnotblank:"+StringUtils.isNotBlank(""));
		System.out.println("isnotblank:"+StringUtils.isNotBlank("1"));
/*			List<Object> list = new ArrayList<Object>();
			Param p1=new Param("12312","haha");
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("code", "2");
			map.put("msg", "3");
			list.add(map);
			Map<String,String> te = (Map<String,String>)list.get(0);
			
			

	
			System.out.println(	"msg:"+	te.get("code")+		"code"+te.get("msg"));
			System.out.println(list.toString());*/
/*			
		 String ids="dwadaw,";
		 System.out.println(ids.substring(0, ids.length()-1));
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("MSGID", 12);
			String id = (Integer) map.get("MSGID")+"";
			System.out.println("id:"+id);
			
			System.out.println(parseCertificateNo("130503670401001").toString());*/
			

	}
	
	public static Map<String,Object> parseCertificateNo(String certificateNo) {  
        
		Map<String,Object>  resultDTO = new HashMap<String,Object>();  
        String myRegExpIDCardNo = "^\\d{6}(((19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])\\d{3}([0-9]|x|X))|(\\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])\\d{3}))$";  
        boolean valid=Pattern.matches(myRegExpIDCardNo,certificateNo)||(certificateNo.length() == 17 && Pattern.matches(myRegExpIDCardNo,certificateNo.substring(0,15)));  
        if(!valid){  
            resultDTO.put("error","证件号码不规范!");  
            return resultDTO;  
        }  
        int idxSexStart = 16;  
        int birthYearSpan = 4;  
        //如果是15位的证件号码  
        if(certificateNo.length() == 15) {  
            idxSexStart = 14;  
            birthYearSpan = 2;  
        }  
          
        //性别  
        String idxSexStr = certificateNo.substring(idxSexStart, idxSexStart + 1);  
        int idxSex = Integer.parseInt(idxSexStr) % 2;  
        String sex = (idxSex == 1) ? "M" : "F";  
        resultDTO.put("sex",sex);  
          
        //出生日期  
        String year = (birthYearSpan == 2 ? "19" : "") + certificateNo.substring(6, 6 + birthYearSpan);  
        String month = certificateNo.substring(6 + birthYearSpan, 6 + birthYearSpan + 2);  
        String day = certificateNo.substring(8 + birthYearSpan, 8 + birthYearSpan + 2);  
        String birthday = year + '-' + month + '-' + day;  
        resultDTO.put("birthday",birthday);  
          
        //年龄  
        Calendar certificateCal = Calendar.getInstance();  
        Calendar currentTimeCal = Calendar.getInstance();  
        certificateCal.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day));  
        int yearAge = (currentTimeCal.get(currentTimeCal.YEAR)) - (certificateCal.get(certificateCal.YEAR));  
        certificateCal.set(currentTimeCal.get(Calendar.YEAR), Integer.parseInt(month)-1, Integer.parseInt(day));  
        int monthFloor = (currentTimeCal.before(certificateCal) ? 1 : 0);  
        resultDTO.put("age",yearAge - monthFloor);  
          
        return resultDTO;  
    }  
	
}
