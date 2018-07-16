package com.daiwei.project.backend.system.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.daiwei.common.log.LogAop;
import com.daiwei.common.page.BTResult;
import com.daiwei.project.backend.system.model.ScheduleJob;
import com.daiwei.utils.BeanKit;
import com.daiwei.utils.Result;
import com.daiwei.utils.Utils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 
* @ClassName: ScheduleJobController 
* @Description: TODO
* @author daiwei
* @date 2017年12月27日 上午11:25:19 
* 
* @version V2.0
 */
@RestController
@RequestMapping("/backend/system/scheduleJob")
@Api(value = "scheduleJob")  
public class ScheduleJobController extends BaseController {
	
	public static final String viewPath = "backend/system/scheduleJob/";
	/**
	 * 展示用户
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:54:21
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@ApiIgnore
	@RequestMapping(value="/show",method=RequestMethod.GET)
	public ModelAndView show(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewPath+"show");
		return mav;
	}
	
	/**
	* 
	* @Title: index 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewPath+"index");
		return mav;
	}

	/**
	 * 新增定时任务
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:56:11
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewPath+"add");
		return mav;
	}
	
	/**
	 * 修改定时任务信息
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:55:54
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@ApiOperation(value = "修改定时任务信息页面", notes = "scheduleJobId")
	@RequestMapping(value="/edit/{scheduleJobId}",method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable long scheduleJobId){
		ModelAndView mav = new ModelAndView();
		ScheduleJob scheduleJob = scheduleJobService.findById(scheduleJobId);
		mav.addObject("scheduleJob", scheduleJob);
		mav.setViewName(viewPath+"edit");
		return mav;
	}
	
	/**
	 * 定时任务列表
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:55:42
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@ApiOperation(value = "获取定时任务列表，支持分页", notes = "json方法获取定时任务列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "定时任务名称", required = true, dataType = "string"),
	@ApiImplicitParam(name = "beginTime", value = "开始时间", required = true, dataType = "string") })
	@RequestMapping(value="/list",method=RequestMethod.POST)
    @ResponseBody
	public  BTResult list(@RequestParam Map<String,Object> map,@RequestParam(required = false) String name, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer deptid){
		List<Map<String,Object>> list = new ArrayList<>();
		//当前页数
		int page = map.get("page")== null ? 0 : Integer.parseInt(map.get("page").toString());
		  // 每页行数
		int size = map.get("size") == null ? 10 : Integer.parseInt(map.get("size").toString());
		
		name =map.get("name")==null?"":name;
		
		Page<ScheduleJob> scheduleJobPages = scheduleJobService.getscheduleJobPageByName(name, page,size);

		List<ScheduleJob> scheduleJobList = scheduleJobPages.getContent();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(ScheduleJob scheduleJob:scheduleJobList){
			Map<String,Object> mapTemp = BeanKit.describe(scheduleJob);
			mapTemp.put("createTime", sdf.format(scheduleJob.getCreateTime()));
			list.add(mapTemp);
		}
		return new BTResult(list,scheduleJobPages.getTotalElements());
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
	@LogAop(action="删除",remark="删除定时任务",targetType="scheduleJob")
	@ApiOperation(value = "删除定时任务", notes = "根据url的ids来指定删除对象或者对象集")
	@ApiImplicitParam(name = "ids", value = "定时任务ids", required = true, dataType = "String")
	@ResponseBody
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
    public Result delete(String ids) {
        List<Long> list = Utils.stringToLongList(ids, ",");
        for(Long id:list){
        	scheduleJobService.delete(id);
        }
		return Result.ok("删除成功!");
	}
	
	/**
	 * 保存定时任务
	 * @param scheduleJob
	 * @return
	 */
	@LogAop(action="新增",remark="新增定时任务",targetType="scheduleJob")
	@ApiOperation(value = "新增保存定时任务", notes = "根据scheduleJob对象操作定时任务")
	@ApiImplicitParam(name = "scheduleJob", value = "定时任务详细实体scheduleJob", required = true, dataType = "scheduleJob")
	@ResponseBody
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public Result save(ScheduleJob scheduleJob){
		ScheduleJob tempscheduleJob = scheduleJobService.save(scheduleJob);	
         return new Result(1,"保存成功！",tempscheduleJob);
    }
	
	
	/**
	 * 修改定时任务
	 * @param scheduleJob
	 * @return
	 */
	@LogAop(action="修改",remark="修改定时任务",targetType="scheduleJob")
	@ApiOperation(value = "修改定时任务", notes = "根据scheduleJob对象操作定时任务")
	@ApiImplicitParam(name = "scheduleJob", value = "定时任务详细实体scheduleJob", required = true, dataType = "scheduleJob")
	@ResponseBody
    @RequestMapping(value="/update",method=RequestMethod.POST)
	//#root.caches[0].name:当前被调用方法所使用的Cache, 即"scheduleJob"
	@CachePut(value = "scheduleJob", key = "#root.caches[0].name + ':' + #scheduleJob.id")
    public Result update(ScheduleJob scheduleJob){
		ScheduleJob tempscheduleJob =  scheduleJobService.save(scheduleJob);	
         return new Result(1,"更新成功！",tempscheduleJob);
	}
}
