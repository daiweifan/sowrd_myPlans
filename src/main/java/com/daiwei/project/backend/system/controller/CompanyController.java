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
import com.daiwei.project.backend.system.model.Company;
import com.daiwei.utils.BeanKit;
import com.daiwei.utils.Result;
import com.daiwei.utils.Utils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;


/** * @author  david:
 * @date 创建时间：2016年6月4日 下午4:21:11
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@RestController
@RequestMapping("/backend/system/company")
@Api(value = "Company")  
public class CompanyController extends BaseController {
	
	public static final String viewPath = "backend/system/company/";
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
	 * 企业首页
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
	 * 新增用户
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
	 * 修改企业信息
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:55:54
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@ApiOperation(value = "修改企业信息页面", notes = "companyId")
	@RequestMapping(value="/edit/{companyId}",method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable long companyId){
		ModelAndView mav = new ModelAndView();
		Company company = companyService.findById(companyId);
		mav.addObject("company", company);
		mav.setViewName(viewPath+"edit");
		return mav;
	}
	
	/**
	 * 企业列表
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:55:42
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@ApiOperation(value = "获取企业列表，支持分页", notes = "json方法获取企业列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "企业名称", required = true, dataType = "string"),
	@ApiImplicitParam(name = "beginTime", value = "开始时间", required = true, dataType = "string") })
	@RequestMapping(value="/list",method=RequestMethod.POST)
    @ResponseBody
	public  BTResult list(@RequestParam Map<String,Object> map){
		List<Map<String,Object>> list = new ArrayList<>();
		//当前页数
		int page = map.get("page")== null ? 0 : Integer.parseInt(map.get("page").toString());
		  // 每页行数
		int size = map.get("size") == null ? 10 : Integer.parseInt(map.get("size").toString());
		
		Page<Company> companyPages = companyService.getCompanysPage(map, page,size);

		List<Company> companyList = companyPages.getContent();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Company company:companyList){
			Map<String,Object> mapTemp = BeanKit.describe(company);
			mapTemp.put("createTime", sdf.format(company.getCreateTime()));
			mapTemp.put("logo", company.getLogo()==null? "/img/default.jpg":company.getLogo().getUrl());
			list.add(mapTemp);
		}
		return new BTResult(list,companyPages.getTotalElements());
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
	@LogAop(action="删除",remark="删除企业",targetType="company")
	@ApiOperation(value = "删除企业", notes = "根据url的ids来指定删除对象或者对象集")
	@ApiImplicitParam(name = "ids", value = "企业ids", required = true, dataType = "String")
	@ResponseBody
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
    public Result delete(String ids) {
        List<Long> list = Utils.stringToLongList(ids, ",");
        companyService.deletedByIds(list);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 保存企业
	 * @param company
	 * @return
	 */
	@LogAop(action="新增",remark="新增企业",targetType="company")
	@ApiOperation(value = "新增保存企业", notes = "根据Company对象操作企业")
	@ApiImplicitParam(name = "company", value = "企业详细实体company", required = true, dataType = "Company")
	@ResponseBody
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public Result save(Company company){
         Company tempCompany = companyService.save(company);	
         return new Result(1,"保存成功！",tempCompany);
    }
	
	
	/**
	 * 修改企业
	 * @param company
	 * @return
	 */
	@LogAop(action="修改",remark="修改企业",targetType="company")
	@ApiOperation(value = "修改企业", notes = "根据Company对象操作企业")
	@ApiImplicitParam(name = "company", value = "企业详细实体company", required = true, dataType = "Company")
	@ResponseBody
    @RequestMapping(value="/update",method=RequestMethod.POST)
	//#root.caches[0].name:当前被调用方法所使用的Cache, 即"company"
	@CachePut(value = "company", key = "#root.caches[0].name + ':' + #company.id")
    public Result update(Company company){
		Company tempCompany =  companyService.save(company);	
         return new Result(1,"更新成功！",tempCompany);
	}
	
	
	/**
	 * 修改企业的状态
	 * 方法功能说明
	 * 创建时间 2018年3月30日 下午3:47:22
	 * 开发者 david
	 * @参数： @param ids
	 * @参数： @param actived
	 * @参数： @return	
	 * @return： Result
	 */
	@LogAop(action="更改状态",remark="更改企业状态",targetType="company")
	@ApiOperation(value = "更改企业状态", notes = "根据Company对象操作企业")
	@ApiImplicitParam(name = "company", value = "企业详细实体company", required = true, dataType = "Company")
	@ResponseBody
    @RequestMapping(value="/changeState",method=RequestMethod.POST)
    public Result changeState(String ids,boolean actived){
        List<Long> list = Utils.stringToLongList(ids, ",");
        companyService.changeStateByIds(list, actived);
         return new Result(1,"修改成功！");
	}
}
