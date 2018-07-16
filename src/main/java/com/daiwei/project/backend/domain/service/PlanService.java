package com.daiwei.project.backend.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.daiwei.project.backend.domain.dao.PlanDao;
import com.daiwei.project.backend.domain.model.Plan;

/**
 * 
* @ClassName: PlanService 
* @Description: TODO
* @author daiwei
* @date 2018年7月16日 下午4:12:59 
* 
* @version V2.0
 */
@Service
public class PlanService  {
	
	@Autowired
	public PlanDao planDao;

	
	public Plan findById(Long id) {
		return planDao.findOne(id);
	}
	
	
	/**
	 * 分页查询 自定义查询条件
	 * 方法功能说明
	 * 创建时间 2018年3月29日 下午2:31:53
	 * 开发者 david
	 * @参数： @param map
	 * @参数： @param page
	 * @参数： @param size
	 * @参数： @return	
	 * @return： Page<Plan>
	 */
	public Page<Plan> getPlansPage(Map<String,Object> map,Integer page,Integer size) {
	     //分页信息
        Pageable pageable = new PageRequest(page,size); //页码：前端从1开始，jpa从0开始，做个转换
        map.remove("page");
        map.remove("size");
		if(!map.isEmpty()){
		    //规格定义
	        Specification<Plan> specification = new Specification<Plan>() {
	            /**
	             * 构造断言
	             * @param root 实体对象引用
	             * @param query 规则查询对象
	             * @param cb 规则构建对象
	             * @return 断言
	             */
	            @Override
	            public Predicate toPredicate(Root<Plan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	                List<Predicate> predicates = new ArrayList<>(); //所有的判断
	                if(StringUtils.isNotBlank(map.get("name").toString())){ //添加断言
	                    Predicate likeName = cb.like(root.get("name").as(String.class),map.get("name").toString()+"%");
	                    predicates.add(likeName);
	                }
	                if(StringUtils.isNotBlank(map.get("order").toString())){ //添加断言
	                    Predicate order = cb.equal(root.get("order").as(String.class),map.get("order").toString());
	                    predicates.add(order);
	                }
	                if(StringUtils.isNotBlank(map.get("beginTime").toString())){ //开始时间
	                    Predicate gteCreateTime = cb.greaterThanOrEqualTo(root.get("createTime").as(String.class),map.get("beginTime").toString());
	                    predicates.add(gteCreateTime);
	                }
	                if(StringUtils.isNotBlank(map.get("endTime").toString())){ //开始时间
	                    Predicate lteCreateTime = cb.lessThanOrEqualTo(root.get("createTime").as(String.class),map.get("endTime").toString());
	                    predicates.add(lteCreateTime);
	                    
	                   
	                }
	                cb.desc(root.get("createTime").as(String.class));
	                cb.asc(root.get("id").as(String.class));
	                return cb.and(predicates.toArray(new Predicate[0]));
	            
	            }
	        };
	        //查询
	        return planDao.findAll(specification,pageable);
		}else{
	        //查询
	        return planDao.findAll(pageable);
		}

    }

	
	/**
	 * 保存用户
	* @Description:  
	* @author david  
	* @date 2017年7月21日 下午3:12:53
	 */
	public Plan save(Plan Plan){
		return planDao.save(Plan);
    }
	
	/**
	 * 物理删除
	 * 方法功能说明
	 * 创建时间 2018年3月31日 上午11:07:34
	 * 开发者 david
	 * @参数： @param id	
	 * @return： void
	 */
	public void delete( long id) {
	    planDao.delete(id);
	}
	
	
	/**
	 * 逻辑删除
	 * 方法功能说明
	 * 创建时间 2018年3月31日 上午11:07:17
	 * 开发者 david
	 * @参数： @param ids	
	 * @return： void
	 */
	public void deletedByIds(List<Long> ids) {
	    planDao.deletedByIds(ids);
	}

}
