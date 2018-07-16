package com.daiwei.project.backend.system.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.daiwei.common.log.LogAop;
import com.daiwei.common.node.ZTreeNode;
import com.daiwei.common.page.BTResult;
import com.daiwei.project.backend.system.model.Dict;
import com.daiwei.project.backend.system.service.DictService;
import com.daiwei.utils.BeanKit;
import com.daiwei.utils.Result;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * @author  david:
 * @date 创建时间：2016年6月4日 下午4:21:11
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@RestController
@RequestMapping("/backend/system/dict")
public class DictController extends BaseController {
	
	public static final String viewPath = "backend/system/dict/";
	
	
	@Autowired
	private DictService dictService;
	
	/**
	 * 字典首页
	 * 方法功能说明
	 * 创建时间 2018年4月2日 下午10:05:20
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewPath+"index");
		return mav;
	}

	/**
	 * 新增字典
	 * 方法功能说明
	 * 创建时间 2018年4月2日 下午10:05:15
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping("/add")
	public ModelAndView add(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewPath+"add");
		return mav;
	}
	
	
	/**
	 * 编辑字典
	 * 方法功能说明
	 * 创建时间 2018年4月2日 下午10:05:04
	 * 开发者 david
	 * @参数： @param id
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable long id){
		ModelAndView mav = new ModelAndView();
		Dict dict = dictService.dictDao.findOne(id);
		mav.addObject("dict", dict);
		mav.addObject("parentName", dict.getParent()==null?null:dict.getParent().getName());
		mav.addObject("parentId", dict.getParent()==null?null:dict.getParent().getId());
		mav.setViewName(viewPath+"edit");
		return mav;
	}
	
	/**
	 * 字典列表
	 * 方法功能说明
	 * 创建时间 2017年7月16日 下午11:55:42
	 * 开发者 david
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@ApiOperation(value = "获取字典列表，支持分页", notes = "json方法获取字典列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "字典名称", required = true, dataType = "string"),
	@ApiImplicitParam(name = "beginTime", value = "开始时间", required = true, dataType = "string") })
	@RequestMapping(value="/list",method=RequestMethod.POST)
    @ResponseBody
	public  BTResult list(@RequestParam Map<String,Object> map){
		List<Map<String,Object>> list = new ArrayList<>();
		//当前页数
		int page = map.get("page")== null ? 0 : Integer.parseInt(map.get("page").toString());
		  // 每页行数
		int size = map.get("size") == null ? 10 : Integer.parseInt(map.get("size").toString());
		
		Page<Dict> dictPages = dictService.getDictsPage(map, page,size);

		List<Dict> dictList = dictPages.getContent();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Dict dict:dictList){
			Map<String,Object> mapTemp = BeanKit.describe(dict);
			if(dict.getParent()==null){
				mapTemp.put("parentCode", dict.getCode());
				mapTemp.put("parentName", dict.getName());
				mapTemp.put("name", "--");
				mapTemp.put("code", "--");
			}else{
				mapTemp.put("parentCode", dict.getParent().getCode());
				mapTemp.put("parentName", dict.getParent().getName());
			}
	
			mapTemp.put("createTime", sdf.format(dict.getCreateTime()));
			list.add(mapTemp);
		}
		return new BTResult(list,dictPages.getTotalElements());
	}
	
	/**
	 * 获取所有字典
	 * 方法功能说明
	 * 创建时间 2017年8月7日 下午10:15:23
	 * 开发者 david
	 * @参数： @param map
	 * @参数： @return	
	 * @return： List<Map<String,Object>>
	 */
	@RequestMapping("/selectDictTreeList")
    @ResponseBody
	public List<ZTreeNode> selectDictTreeList(@RequestParam Map<String,Object> map){
		List<ZTreeNode> list = new ArrayList<ZTreeNode>();
	     List<Dict> dictList = dictService.dictDao.findAllParent();
	     list.add(ZTreeNode.createParent());
	     for(Dict dict:dictList){
	    	 list.add(new ZTreeNode(dict.getId(),dict.getParent()==null? 0l:dict.getParent().getId(),dict.getName(),true,true));
	     }
	     return list;
	}
	
	/**
	 * 删除字典
	 * 方法功能说明
	 * 创建时间 2018年4月2日 下午10:11:48
	 * 开发者 david
	 * @参数： @param ids
	 * @参数： @return	
	 * @return： Result
	 */
	@LogAop(action="删除",remark="删除字典",targetType="dict")
	@ResponseBody
	@RequestMapping(value = "/delete")
    public Result delete(String ids) {
		return dictService.delete(ids);
	}
	
	
	/**
	 * 新增保存
	 * 方法功能说明
	 * 创建时间 2018年4月2日 下午10:12:09
	 * 开发者 david
	 * @参数： @param dict
	 * @参数： @return	
	 * @return： Result
	 */
	@LogAop(action="新增",remark="新增字典",targetType="dict")
	@ResponseBody
    @RequestMapping(value="/save")
    public Result save(Dict dict){
        return  dictService.save(dict);	
    }
	
	/**
	 * 修改
	 * 方法功能说明
	 * 创建时间 2017年8月6日 下午9:28:43
	 * 开发者 david
	 * @参数： @param dict
	 * @参数： @return	
	 * @return： Result
	 */
	@LogAop(action="修改",remark="修改字典",targetType="dict")
	@ResponseBody
    @RequestMapping(value="/update")
    public Result update(Dict dict){
        return  dictService.save(dict);	
    }
	
}
