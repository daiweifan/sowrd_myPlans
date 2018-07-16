package com.daiwei.project.website.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.daiwei.project.backend.system.controller.BaseController;

/** * @author  david:
 * @date 创建时间：2016年5月27日 下午3:12:58
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@RestController
public class WebsiteController extends BaseController{

	@RequestMapping("/")
	public ModelAndView homePage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/website/homePage");
		return mav;
	}
	
	
	@RequestMapping("/authors")
	public ModelAndView authors(String search){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/website/authors");
		return mav;
	}
}
