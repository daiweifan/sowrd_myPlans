package com.daiwei.project.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.daiwei.common.log.LogAop;
import com.daiwei.common.page.BTResult;
import com.daiwei.project.backend.domain.model.Plan;
import com.daiwei.project.backend.domain.service.PlanService;
import com.daiwei.project.backend.system.controller.BaseController;
import com.daiwei.utils.BeanKit;
import com.daiwei.utils.Result;
import com.daiwei.utils.Utils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 
* @ClassName: ApiPlanController 
* @Description: TODO
* @author daiwei
* @date 2018年7月16日 下午3:59:28 
* 
* @version V2.0
 */
@RestController
@RequestMapping("/api/plan")
@Api(value = "plan")  
public class ApiPlanController extends BaseController {
	

	@Autowired
	private PlanService PlanService;
	
	
	/**
	 * 单个计划查询
	 * @param Plan
	 * @return
	 */
	@LogAop(action="查询",remark="单个计划查询",targetType="Plan")
	@ApiOperation(value = "单个计划查询", notes = "根据Plan对象操作计划")
	@ApiImplicitParam(name = "Plan", value = "计划详细实体Plan", required = true, dataType = "Plan")
	@ResponseBody
    @RequestMapping(value="/findById",method=RequestMethod.GET)
    public Result findById(Long id){
         Plan tempPlan = PlanService.findById(id);
         SimpleDateFormat sdf = new SimpleDateFormat();

         return new Result(1,"查询成功！",tempPlan);
    }
	
	/**
	 * 计划列表
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:55:42
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@ApiOperation(value = "获取计划列表，支持分页", notes = "json方法获取计划列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "计划名称", required = true, dataType = "string"),
	@ApiImplicitParam(name = "beginTime", value = "开始时间", required = true, dataType = "string") })
	@RequestMapping(value="/list",method=RequestMethod.POST)
    @ResponseBody
	public  BTResult list(@RequestParam Map<String,Object> map){
		List<Map<String,Object>> list = new ArrayList<>();
		//当前页数
		int page = map.get("page")== null ? 0 : Integer.parseInt(map.get("page").toString());
		  // 每页行数
		int size = map.get("size") == null ? 10 : Integer.parseInt(map.get("size").toString());
		
		Page<Plan> PlanPages = PlanService.getPlansPage(map, page,size);

		List<Plan> PlanList = PlanPages.getContent();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Plan Plan:PlanList){
			Map<String,Object> mapTemp = BeanKit.describe(Plan);
			mapTemp.put("createTime", sdf.format(Plan.getCreateTime()));
			list.add(mapTemp);
		}
		return new BTResult(list,PlanPages.getTotalElements());
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
	@LogAop(action="删除",remark="删除计划",targetType="Plan")
	@ApiOperation(value = "删除计划", notes = "根据url的ids来指定删除对象或者对象集")
	@ApiImplicitParam(name = "ids", value = "计划ids", required = true, dataType = "String")
	@ResponseBody
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
    public Result delete(String ids) {
        List<Long> list = Utils.stringToLongList(ids, ",");
        PlanService.deletedByIds(list);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 保存计划
	 * @param Plan
	 * @return
	 */
	@LogAop(action="新增",remark="新增计划",targetType="Plan")
	@ApiOperation(value = "新增保存计划", notes = "根据Plan对象操作计划")
	@ApiImplicitParam(name = "Plan", value = "计划详细实体Plan", required = true, dataType = "Plan")
	@ResponseBody
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public Result save(Plan plan){
         Plan tempPlan = PlanService.save(plan);	
         return new Result(1,"保存成功！",tempPlan);
    }
	
	
	/**
	 * 修改计划
	 * @param Plan
	 * @return
	 */
	@LogAop(action="修改",remark="修改计划",targetType="Plan")
	@ApiOperation(value = "修改计划", notes = "根据Plan对象操作计划")
	@ApiImplicitParam(name = "Plan", value = "计划详细实体Plan", required = true, dataType = "Plan")
	@ResponseBody
    @RequestMapping(value="/update",method=RequestMethod.POST)
	//#root.caches[0].name:当前被调用方法所使用的Cache, 即"Plan"
	@CachePut(value = "Plan", key = "#root.caches[0].name + ':' + #Plan.id")
    public Result update(Plan plan){
		Plan tempPlan =  PlanService.save(plan);	
         return new Result(1,"更新成功！",tempPlan);
	}
	

}
