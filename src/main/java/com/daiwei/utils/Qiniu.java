package com.daiwei.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qiniu.util.Auth;

/** * @author  david:
 * @date 创建时间：2017年3月30日 下午9:49:37
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
public class Qiniu {

//	static Auth auth = Auth.create("xk4QY0WBlCmEfu7F4WyeeX2SYvUDxgRiudQAk2dL", "z4oCHtDpSYdCkp4uUXUjbJu1Sk0uCQ5UfWcTFEgb");
	static Auth auth = Auth.create("zzKSfuzQbatLHnpDuIA1_e6UI_vU1Hs0GdcDxOvY", "hDyVEOEDVhb9FvOZMXdsoRhW4mxJrfnmQKmXtcJ7");

	Log log = LogFactory.getLog(Qiniu.class);

//	public static String qiniu_url = "http://onmqcrorp.bkt.clouddn.com";
	public static String qiniu_url = "http://oocm530n5.bkt.clouddn.com";

	
	public String getUploadToken() {
		return auth.uploadToken("muji");
	}
	
}