package com.daiwei.project.backend.system.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daiwei.project.backend.system.dao.RoleDao;
import com.daiwei.project.backend.system.dao.UserDao;
import com.daiwei.project.backend.system.model.FileInfo;
import com.daiwei.project.backend.system.model.Role;
import com.daiwei.project.backend.system.model.User;
import com.daiwei.utils.BeanKit;
import com.daiwei.utils.Result;
import com.daiwei.utils.StringKit;
import com.daiwei.utils.Utils;

/** * @author  david:
 * @date 创建时间：2017年6月28日 下午9:16:07
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@Service
public class UserService  {
	
	@Autowired
	public UserDao userDao;
	
	@Autowired
	public RoleDao roleDao;
	
	@Autowired
	public FileService fileService;

	/**
	 * 根据用户名获取用户
	 * 方法功能说明
	 * 创建时间 2017年7月20日 下午9:15:39
	 * 开发者 david
	 * @参数： @param name
	 * @参数： @return	
	 * @return： User
	 */
	public User findByUsername(String name) {
		return userDao.findByUsername(name);
	}
	
	public User findById(Long id) {
        System.out.println("无缓存的时候调用这里1111");  
		return userDao.findById(id);
	}
	
	/**  
	 * 保存用户
	* @Description:  
	* @author david  
	* @date 2017年7月21日 下午3:12:53
	 */
	public Result save(User user){
		
/*		 userDao.save(user);
		 return new Result(1,"保存成功！",null);*/
		User userTemp = null;
		if(user.getId()!=null){
			userTemp = userDao.findOne(user.getId());
		}
        if(userTemp ==null){
        	userTemp= new User();
        }
        List<Role> roles = userTemp.getRoles();
        userTemp = user;
        if(user.getId()==null){
        	userTemp.setPassword(new Sha256Hash(userTemp.getPassword()).toHex());
        }
        userTemp.setRoles(roles);
        boolean repeat = false;
        User userCheck = userDao.findByUsername(user.getUsername());
        //判断用户名是否存在
        if(userCheck!=null){
            if(userTemp.getId()==null){
                repeat =true;
            }else{
                if(userTemp.getId()!=userCheck.getId()){
                    repeat =true;
                }
            }
        }
        if(repeat){
            return new Result(2,"用户名[<span style='font-weight:bold;color:red'>"+user.getUsername()+"</span>]已经存在！");
        }else{
        	  userDao.save(userTemp);
            return new Result(1,"保存成功！", BeanKit.describe(userTemp, "roles","avatar"));
        }
    }
	
	/**
	 * 设置角色
	 * 方法功能说明
	 * 创建时间 2017年9月5日 下午8:41:48
	 * 开发者 david
	 * @参数： @param userId
	 * @参数： @param roleIds
	 * @参数： @return	
	 * @return： Result
	 */
	public Result setRoles(long userId,String roleIds){
		List<Role> roles = new ArrayList<Role>();
		 List<Long> list = Utils.stringToLongList(roleIds, ",");
		 for(Long id:list){
	        	Role role = roleDao.findOne(id);
	        	roles.add(role);
		 }
		User user=userDao.findOne(userId);
		user.setRoles(roles);
		userDao.save(user);
		return new Result(1,"保存成功！",null);
    }
	
	
	/**  
	 * 修改个人资料
	 * 方法功能说明
	 * 创建时间 2017年9月7日 下午8:50:21
	 * 开发者 david
	 * @参数： @param map
	 * @参数： @return	
	 * @return： Result
	 */
	public Result updateShow(Map<String,Object> map){
		
		String id = map.get("id").toString();
		if(!StringKit.isBlank(id)){
			User user = userDao.getOne(Long.valueOf(id));
			String realname = map.get("realname").toString();
			int sex = Integer.parseInt(map.get("sex").toString());
			String birthday = map.get("birthday").toString();
			String phone = map.get("phone").toString();
			String email = map.get("email").toString();
			long avatar = Long.parseLong(map.get("avatar").toString());
			FileInfo file = fileService.fileDao.findOne(avatar);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			user.setRealname(realname);
			user.setPhone(phone);
			user.setEmail(email);
			user.setAvatar(file);
			try {
				user.setBirthday(sdf.parse(birthday));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user.setSex(sex);
			userDao.save(user);
		//	 return new Result(1,"保存成功！", BeanKit.describe(user, "roles","avatar"));
			 return new Result(1,"保存成功！","修改用户信息");
		}else{
			 return new Result(2,"用户id不能为空！");
		}
       
    }
}
