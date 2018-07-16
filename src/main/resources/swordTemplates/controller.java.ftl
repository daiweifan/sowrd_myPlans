package ${bean.packageUrl}.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ${bean.packageUrl}.model.${bean.name};
import ${bean.packageUrl}.service.${bean.name}Service;
import com.daiwei.utils.BeanKit;
import com.daiwei.utils.Result;
import com.daiwei.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;

/** 
 * @author  ${bean.authorName}(${bean.authorMail})
 * @date 创建时间：${bean.date}
 * @version ${bean.version}
 * @parameter
 * @since 
 * @return 
 */
@RestController
@RequestMapping("/backend/system/${bean.lowerName}")
public class ${bean.name}Controller extends BaseController {
	
	public static final String viewPath = "backend/system/${bean.lowerName}/";
	
	@Autowired
	private ${bean.name}Service ${bean.lowerName}Service;
	/**
	 * 展示${bean.content}
	 * 方法功能说明
	 * 创建时间 ${bean.date}
	 * 开发者 ${bean.authorName}
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping("/show")
	public ModelAndView show(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewPath+"show");
		return mav;
	}
	
	/**
	 * ${bean.content}首页
	* @Description:  
	* @author ${bean.authorName} 
	* @date ${bean.date}
	 */
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewPath+"index");
		return mav;
	}

	/**
	 * 新增${bean.content}
	 * 方法功能说明
	 * 创建时间${bean.date}
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
	 * 修改${bean.content}信息
	 * 方法功能说明
	 * 创建时间 ${bean.date}
	 * 开发者${bean.authorName} 
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable long id){
		ModelAndView mav = new ModelAndView();
		${bean.name} ${bean.lowerName} = ${bean.lowerName}Service.${bean.lowerName}Dao.findOne(id);
		mav.addObject("${bean.lowerName}", ${bean.lowerName});
		mav.setViewName(viewPath+"edit");
		return mav;
	}
	
	/**
	 * ${bean.content}列表
	 * 方法功能说明
	 * 创建时间 ${bean.date}
	 * 开发者 ${bean.authorName} 
	 * @参数： @return	
	 * @return： ModelAndView
	 */
	@RequestMapping("/list")
    @ResponseBody
	public List<Map<String,Object>> list(@RequestParam Map<String,Object> map,@RequestParam(required = false) String name, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer deptid){
		List<Map<String,Object>> list = new ArrayList<>();
		
		List<${bean.name}> ${bean.lowerName}List = (List<${bean.name}>) ${bean.lowerName}Service.${bean.lowerName}Dao.findAll();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(${bean.name} ${bean.lowerName}:${bean.lowerName}List){
			Map<String,Object> mapTemp = BeanKit.describe(${bean.lowerName});
			mapTemp.put("createTime", sdf.format(${bean.lowerName}.getCreateTime()));
			list.add(mapTemp);
		}

		return list;
	}
	
	/**
	 * 删除
	 * 方法功能说明
	 * 创建时间${bean.date}
	 * 开发者 ${bean.authorName} 
	 * @参数： @param ids
	 * @参数： @return	
	 * @return： Result
	 */
	@ResponseBody
	@RequestMapping(value = "/delete")
    public Result delete(String ids) {
        List<Long> list = Utils.stringToLongList(ids, ",");
        for(Long id:list){
        	${bean.lowerName}Service.${bean.lowerName}Dao.delete(id);
        }
		return Result.ok("删除成功!");
	}
	
   /**
	 * 保存/修改
	 * 方法功能说明
	 * 创建时间 ${bean.date}
	 * 开发者 ${bean.authorName} 
	 * @参数： @param ${bean.lowerName}
	 * @参数： @return	
	 * @return： Result
	 */
	@ResponseBody
    @RequestMapping(value="/save")
    public Result save(${bean.name} ${bean.lowerName}){
        return  ${bean.lowerName}Service.save(${bean.lowerName});	
    }
}
