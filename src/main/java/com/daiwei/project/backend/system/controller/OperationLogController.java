package com.daiwei.project.backend.system.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.daiwei.common.page.BTResult;
import com.daiwei.common.page.SortBuilder;
import com.daiwei.project.backend.system.model.OperationLog;
import com.daiwei.project.backend.system.service.OperationLogService;
import com.daiwei.utils.BeanKit;
import com.daiwei.utils.Result;
import com.daiwei.utils.StringKit;
import com.daiwei.utils.Utils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 操作日志管理
 *  @author  david:
 * @date 创建时间：2017年9月2日 下午4:21:11
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@RestController
@RequestMapping("/backend/system/operationLog")
@Api(value = "operationLog")  
public class OperationLogController extends BaseController {
	
	public static final String viewPath = "backend/system/operationLog/";
	
	@Autowired
	private OperationLogService operationLogService;
	
	/**
	 * 日志首页
	* @Description:  
	* @author david  
	* @date 2017年7月20日 下午4:18:22
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewPath+"index");
		return mav;
	}
	
	/**
	 * 展示日志信息
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:55:54
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping(value="/show/{operationLogId}",method=RequestMethod.GET)
	public ModelAndView show(@PathVariable long operationLogId){
		ModelAndView mav = new ModelAndView();
		OperationLog operationLog = operationLogService.operationLogDao.findOne(operationLogId);
		mav.addObject("operationLog", operationLog);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mav.addObject("createTime", sdf.format(operationLog.getCreateTime()));
		mav.addObject("username", operationLog.getUser().getUsername());
		mav.setViewName(viewPath+"show");
		return mav;
	}
	
	/**
	 * 操作日志列表
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:55:42
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@ApiOperation(value = "获取企业列表，支持分页", notes = "json方法获取用户列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "企业名称", required = true, dataType = "string"),
	@ApiImplicitParam(name = "beginTime", value = "开始时间", required = true, dataType = "string") })
	@RequestMapping(value="/list",method=RequestMethod.POST)
    @ResponseBody
	public  BTResult list(@RequestParam Map<String,Object> map,@RequestParam(required = false) String name, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer deptid){
		List<Map<String,Object>> list = new ArrayList<>();
		//当前页数
		int page = map.get("page")== null ? 0 : Integer.parseInt(map.get("page").toString());
		  // 每页行数
		int size = map.get("size") == null ? 10 : Integer.parseInt(map.get("size").toString());
		
		Pageable pageable = new PageRequest(page,size,SortBuilder.generateSort("createTime desc","id asc"));
		Page<OperationLog> operationLogPages = null;
		if(StringKit.isEmpty(name)){
			operationLogPages = operationLogService.operationLogDao.findAll(pageable);
		}else{
			operationLogPages = operationLogService.operationLogDao.findByNameLike("%"+name+"%",pageable);
		}

		List<OperationLog> operationLogList = operationLogPages.getContent();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(OperationLog operationLog:operationLogList){
			Map<String,Object> mapTemp = BeanKit.describe(operationLog,"user");
			mapTemp.put("createTime", sdf.format(operationLog.getCreateTime()));
			mapTemp.put("username", operationLog.getUser().getUsername());
			list.add(mapTemp);
		}
		return new BTResult(list,operationLogPages.getTotalElements());
	}
	
	/**
	 * 删除
	 * 方法功能说明
	 * 创建时间 2017年7月25日 下午10:59:17
	 * 开发者 david
	 * @参数： @param ids
	 * @参数： @return	
	 * @return： Result
	 */
	@ApiOperation(value = "删除日志", notes = "根据url的ids来指定删除对象或者对象集")
	@ApiImplicitParam(name = "ids", value = "日志ids", required = true, dataType = "String")
	@ResponseBody
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
    public Result delete(String ids) {
        List<Long> list = Utils.stringToLongList(ids, ",");
        for(Long id:list){
        	operationLogService.operationLogDao.delete(id);
        }
		return Result.ok("删除成功!");
	}
	
}
