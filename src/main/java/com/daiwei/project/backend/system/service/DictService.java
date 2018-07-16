package com.daiwei.project.backend.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.daiwei.project.backend.system.dao.DictDao;
import com.daiwei.project.backend.system.model.Dict;
import com.daiwei.utils.Result;
import com.daiwei.utils.Utils;

import cn.hutool.core.util.StrUtil;

/** 
 * @author  david:
 * @date 创建时间：2017年6月28日 下午9:16:07
 * @version 1.0
 * @parameter
 * @since 
 * @return 
 */
@Service
public class DictService  {
	
	@Autowired
	public DictDao dictDao;
	
	/**
	 * 保存字典
	 * 方法功能说明
	 * 创建时间 2018年4月2日 下午10:00:44
	 * 开发者 david
	 * @参数： @param Dict
	 * @参数： @return	
	 * @return： Result
	 */
	public Result save(Dict Dict){
		Dict dictTemp = null;
        if(Dict.getId() ==null){
        	dictTemp= new Dict();
        }else{
        	dictTemp = dictDao.findOne(Dict.getId());
        }
        dictTemp = Dict;
        boolean repeat = false;
        Dict DictCheck = null;
        if(dictTemp.getParent()!=null){
        	DictCheck = dictDao.findByCodeAndParent(dictTemp.getCode(), dictTemp.getParent().getId());
        }else{
        	DictCheck = dictDao.findByCode(dictTemp.getCode());
        }
        //判断字典是否存在
        if(DictCheck!=null){
            if(dictTemp.getId()==null){
                repeat =true;
            }else{
                if(dictTemp.getId()!=DictCheck.getId()){
                    repeat =true;
                }
            }
        }
        if(dictTemp.getParent()==null){
        	dictTemp.setType(dictTemp.getCode());
        }else{
        	dictTemp.setType(dictTemp.getParent().getCode());
        }
        if(repeat){
            return new Result(2,"字典编码[<span style='font-weight:bold;color:red'>"+Dict.getCode()+"</span>]已经存在！");
        }else{
        	  dictDao.save(dictTemp);
            return new Result(1,"保存成功！",dictTemp);
        }
    }
	

	
	/**
	 * 逻辑删除
	 * 方法功能说明
	 * 创建时间 2018年4月2日 下午9:57:24
	 * 开发者 david
	 * @参数： @param ids
	 * @参数： @return	
	 * @return： Result
	 */
    public Result delete(String ids) {
        List<Long> list = Utils.stringToLongList(ids, ",");
        dictDao.deletedByIds(list);
		return Result.ok("删除成功!");
	}
    
    
	/**
	 * 分页查询 自定义查询条件
	 * 方法功能说明
	 * 创建时间 2018年4月2日 下午11:12:32
	 * 开发者 david
	 * @参数： @param map
	 * @参数： @param page
	 * @参数： @param size
	 * @参数： @return	
	 * @return： Page<Dict>
	 */
	public Page<Dict> getDictsPage(Map<String,Object> map,Integer page,Integer size) {
		 List<Order> orders = new ArrayList<Order>();
	     orders.add( new Order(Direction.DESC, "type"));
	     orders.add( new Order(Direction.ASC, "sort"));
	     //分页信息
        Pageable pageable = new PageRequest(page,size,new Sort(orders)); //页码：前端从1开始，jpa从0开始，做个转换
        map.remove("page");
        map.remove("size");
		if(!map.isEmpty()){
		    //规格定义
	        Specification<Dict> specification = new Specification<Dict>() {
	            /**
	             * 构造断言
	             * @param root 实体对象引用
	             * @param query 规则查询对象
	             * @param cb 规则构建对象
	             * @return 断言
	             */
	            @Override
	            public Predicate toPredicate(Root<Dict> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	                List<Predicate> predicates = new ArrayList<>(); //所有的判断
	                if(!StrUtil.isBlankIfStr(map.get("dictName"))){ //添加断言
	                    Predicate likeName = cb.like(root.get("name").as(String.class),map.get("dictName").toString()+"%");
	                    predicates.add(likeName);
	                }
	                if(!StrUtil.isBlankIfStr(map.get("dictCode"))){ //添加断言
	                    Predicate likeName = cb.like(root.get("type").as(String.class),map.get("dictCode").toString()+"%");
	                    predicates.add(likeName);
	                }
	                if(!StrUtil.isBlankIfStr(map.get("beginTime"))){ //开始时间
	                    Predicate gteCreateTime = cb.greaterThanOrEqualTo(root.get("createTime").as(String.class),map.get("beginTime").toString());
	                    predicates.add(gteCreateTime);
	                }
	                if(!StrUtil.isBlankIfStr(map.get("endTime"))){ //开始时间
	                    Predicate lteCreateTime = cb.lessThanOrEqualTo(root.get("createTime").as(String.class),map.get("endTime").toString());
	                    predicates.add(lteCreateTime);
	                    
	                   
	                }
	                
	                cb.desc(root.get("type").as(String.class));
	                cb.asc(root.get("sort").as(String.class));
	                return cb.and(predicates.toArray(new Predicate[0]));
	            
	            }
	        };
	        //查询
	        return dictDao.findAll(specification,pageable);
		}else{
	        //查询
	        return dictDao.findAll(pageable);
		}

    }	
}
