package com.daiwei.project.backend.system.service;

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

import com.daiwei.common.page.SortBuilder;
import com.daiwei.project.backend.system.dao.CompanyDao;
import com.daiwei.project.backend.system.model.Company;
import com.daiwei.utils.StringKit;

/** * @author  david:
 * @date 创建时间：2017年6月28日 下午9:16:07
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@Service
public class CompanyService  {
	
	@Autowired
	public CompanyDao companyDao;

	/**
	 * 根据用户名获取用户
	 * 方法功能说明
	 * 创建时间 2017年7月20日 下午9:15:39
	 * 开发者 david
	 * @参数： @param name
	 * @参数： @return	
	 * @return： User
	 */
	public Company findByName(String name) {
		return companyDao.findByName(name);
	}
	
	public Company findById(Long id) {
		return companyDao.findOne(id);
	}
	
	
	public Page<Company> getCompanyPageByName(String name,Integer page,Integer size ) {
		Pageable pageable = new PageRequest(page,size,SortBuilder.generateSort("createTime desc","id asc"));
		if(StringKit.isEmpty(name)){
			return companyDao.findAll(pageable);
		}else{
			return companyDao.findByNameLike("%"+name+"%",pageable);
		}
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
	 * @return： Page<Company>
	 */
	public Page<Company> getCompanysPage(Map<String,Object> map,Integer page,Integer size) {
	     //分页信息
        Pageable pageable = new PageRequest(page,size); //页码：前端从1开始，jpa从0开始，做个转换
        map.remove("page");
        map.remove("size");
		if(!map.isEmpty()){
		    //规格定义
	        Specification<Company> specification = new Specification<Company>() {
	            /**
	             * 构造断言
	             * @param root 实体对象引用
	             * @param query 规则查询对象
	             * @param cb 规则构建对象
	             * @return 断言
	             */
	            @Override
	            public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	                List<Predicate> predicates = new ArrayList<>(); //所有的判断
	                if(StringUtils.isNotBlank(map.get("name").toString())){ //添加断言
	                    Predicate likeName = cb.like(root.get("name").as(String.class),map.get("name").toString()+"%");
	                    predicates.add(likeName);
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
	        return companyDao.findAll(specification,pageable);
		}else{
	        //查询
	        return companyDao.findAll(pageable);
		}

    }

	
	/**
	 * 保存用户
	* @Description:  
	* @author david  
	* @date 2017年7月21日 下午3:12:53
	 */
	public Company save(Company company){
		return companyDao.save(company);
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
	    companyDao.delete(id);
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
	    companyDao.deletedByIds(ids);
	}
	/**
	 * 修改deleted状态
	 * 方法功能说明
	 * 创建时间 2018年3月30日 下午3:36:32
	 * 开发者 david
	 * @参数： @param id
	 * @参数： @param actived	
	 * @return： void
	 */
	public void changeStateByIds( List<Long> ids,boolean actived) {
	    companyDao.changeStateByIds(ids, actived);
	}
}
