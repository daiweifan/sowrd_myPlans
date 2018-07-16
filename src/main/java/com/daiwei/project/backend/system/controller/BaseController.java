package com.daiwei.project.backend.system.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.daiwei.common.properties.SwordConfig;
import com.daiwei.common.support.HttpKit;
import com.daiwei.project.backend.system.service.CompanyService;
import com.daiwei.project.backend.system.service.FileService;
import com.daiwei.project.backend.system.service.MenuService;
import com.daiwei.project.backend.system.service.RoleService;
import com.daiwei.project.backend.system.service.ScheduleJobService;
import com.daiwei.project.backend.system.service.UserService;
import com.daiwei.utils.DateEditor;
import com.daiwei.utils.DoubleEditor;
import com.daiwei.utils.FloatEditor;
import com.daiwei.utils.IntegerEditor;
import com.daiwei.utils.LongEditor;



/**
 * 公共组件
 * @author  david:
 * @date 创建时间：2016年6月4日 下午4:20:11
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
public abstract class BaseController {

	@Autowired
	public UserService userService;
	
	@Autowired
	public RoleService roleService;
	
	@Autowired
	public CompanyService companyService;
	
	@Autowired
	protected HttpServletRequest req;
	
	@Autowired
	protected HttpServletResponse res;
	
	@Autowired
	public ScheduleJobService scheduleJobService;
	
	@Autowired
	public MenuService menuService;
    
	@Autowired
	public SwordConfig swordConfig;
	
	@Autowired
	public FileService fileService;
	
    protected HttpServletRequest getHttpServletRequest() {
        return HttpKit.getRequest();
    }

    protected HttpServletResponse getHttpServletResponse() {
        return HttpKit.getResponse();
    }
    /**
     * Inits the binder.
     *
     * @param binder the binder
     */
    @InitBinder  
    protected  void initBinder(WebDataBinder binder) {  
        binder.registerCustomEditor(Date.class, new DateEditor()); 
        binder.registerCustomEditor(long.class, new LongEditor());    
        binder.registerCustomEditor(double.class, new DoubleEditor());    
        binder.registerCustomEditor(float.class, new FloatEditor());
        binder.registerCustomEditor(int.class, new IntegerEditor());
    }
    
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
}
