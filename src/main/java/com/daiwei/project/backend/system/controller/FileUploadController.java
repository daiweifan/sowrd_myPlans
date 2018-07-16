package com.daiwei.project.backend.system.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.daiwei.common.exception.BizExceptionEnum;
import com.daiwei.common.exception.BussinessException;
import com.daiwei.common.log.LogAop;
import com.daiwei.project.backend.system.model.FileInfo;
import com.daiwei.project.backend.system.model.User;
import com.daiwei.utils.Result;
import com.daiwei.utils.ShiroKit;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文件上传
 * @author david
 *
 */
@Api(value = "FileUpload")  
@Controller
@RequestMapping(value = "/backend/system/upload")
public class FileUploadController extends BaseController {

    /**
     * 上传图片(上传到项目的webapp/static/img)
     */
	@LogAop(action="上传文件",remark="上传文件",targetType="file")
	@ApiOperation(value = "上传文件", notes = "上传单个文件")
    @ResponseBody
    @RequestMapping(value = "/single" ,method = RequestMethod.POST)
    public Result uploadSingle(HttpServletRequest request,HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("UTF-8");

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        
        /** 页面控件的文件流* */
        MultipartFile multipartFile = null;
        Map<String,MultipartFile> map =multipartRequest.getFileMap();
         for (Iterator<String> i = map.keySet().iterator(); i.hasNext();) {
                Object obj = i.next();
                multipartFile=(MultipartFile) map.get(obj);

               }
       
        /** 获取文件的后缀* */
        String fileName = multipartFile.getOriginalFilename();
        System.out.println(swordConfig.getCtx());
        System.out.println(swordConfig.getAuthor());
        System.out.println(swordConfig.getUploadPath());
        System.out.println("fileName:"+fileName+"||"+ multipartFile.getName()+"||"+multipartFile.getSize());
        // 文件保存路径  
        String filePath ="";
        if(StrUtil.isBlankOrUndefined(swordConfig.getUploadPath())){
        	filePath = request.getSession().getServletContext().getRealPath("/") + "images/"  ;  
        }else{
        	filePath = swordConfig.getUploadPath()+"images/";
        }
 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String suffixName = fileName.trim().split("\\.")[1];
        FileInfo fileInfo = new FileInfo();
        String picName = UUID.randomUUID().toString() + "."+suffixName;
        String day = sdf.format(new Date());
        
        String url =  filePath.split(":")[1]+day+"/"+picName;//完整的url
        fileInfo.setSuffix(suffixName);
        System.out.println("用户id:"+ShiroKit.getUserId());
        User user = userService.userDao.findOne(ShiroKit.getUserId());
        fileInfo.setUser(user);
        fileInfo.setSize(multipartFile.getSize());
        fileInfo.setIp(request.getRemoteAddr());
        fileInfo.setName(fileName);
        fileInfo.setUrl(url);
        fileInfo.setType(0);//本地存储
        fileService.save(fileInfo);
        // 解决中文问题，liunx下中文路径，图片显示问题
        File dest = new File(filePath +"/"+day+"/"+ picName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
          dest.getParentFile().mkdirs();
        }
        if (!multipartFile.isEmpty()) { 
               try {
                   // 转存文件  
                   multipartFile.transferTo(dest);  
               } catch (Exception e) {
                   throw new BussinessException(BizExceptionEnum.UPLOAD_ERROR);
               }

        }
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("path", filePath);
        data.put("id", fileInfo.getId());
        return new Result(1,"上传成功！",data);
    }
	
}
