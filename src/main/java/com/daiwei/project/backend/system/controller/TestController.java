package com.daiwei.project.backend.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daiwei.utils.BeanKit;

import springfox.documentation.annotations.ApiIgnore;

/** 
 * @author  david:
 * @date 创建时间：2017年12月22日 下午3:12:58
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@ApiIgnore
@RestController
@RequestMapping("/test")
public class TestController extends BaseController{	
	
	
	public static final String viewPath = "test";
	
	/**
	 * 
	 * 方法功能说明
	 * 创建时间 2017年6月28日 下午10:10:25
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping("/list")
	public List<Map<String,Object>> testdata(@RequestParam(required = false) String name, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime){
		List<Map<String,Object>> list = new ArrayList<>();
		for(int i=0;i<40;i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", 1);
			map.put("account", "admin"+i);
			map.put("name", "david"+i);
			map.put("sexName", "男");
			list.add(map);
		}
        return list;
	}
	
	
	@RequestMapping("/getUser/{id}")
	public Map<String,Object> getUser(@PathVariable long id) {
		Map<String,Object> user=BeanKit.describe(userService.findById(id), "roles");
	    System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");  
	    return user;
	}
}
