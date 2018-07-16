package com.daiwei.common.log;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.daiwei.common.properties.SwordConfig;
import com.daiwei.common.support.HttpKit;
import com.daiwei.project.backend.system.model.LoginLog;
import com.daiwei.project.backend.system.model.OperationLog;
import com.daiwei.project.backend.system.model.User;
import com.daiwei.project.backend.system.service.LoginLogService;
import com.daiwei.project.backend.system.service.OperationLogService;
import com.daiwei.utils.Result;
import com.daiwei.utils.ShiroKit;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

/**
 * log aop控制
 * @author david
 *
 */
@Aspect // FOR AOP
@Order(-99) // 控制多个Aspect的执行顺序，越小越先执行
@Component
public class LogAspect {
	
	private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);
	
	@Autowired
	private LoginLogService loginLogService;
	
	@Autowired
	private OperationLogService operationLogService;
	
	@Autowired
	private SwordConfig swordConfig;
	
	
	
//	@Pointcut("execution(public * com.daiwei.project.backend.system.controller.*.*(..))")//两个..代表所有子目录，最后括号里的两个..代表所有参数
//    public void logPointCut() {
//    }
	
	@Pointcut("@annotation(com.daiwei.common.log.LogAop)")//只针对添加这个注解的方法
    public void logPointCut() {
    }
	
	
	   @Before("logPointCut()")
	    public void doBefore(JoinPoint joinPoint) throws Throwable {
	       LOG.info("之前");

	    }

	    @AfterReturning(returning = "ret", pointcut = "logPointCut()")// returning的值和doAfterReturning的参数名一致
	    public void doAfterReturning(Object ret) throws Throwable {
	        // 处理完请求，返回内容
	    	LOG.info("处理完成");
	        LOG.info("返回值 : " + ret);
	    }

	    @Around("logPointCut()")
	    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
	        long startTime = System.currentTimeMillis();
	        Object ob = pjp.proceed();// ob 为方法的返回值
	        long  resTime = (System.currentTimeMillis() - startTime)/1000;//秒
	        //获取拦截的方法名
	        Signature sig = pjp.getSignature();
	        MethodSignature msig = null;
	        if (!(sig instanceof MethodSignature)) {
	            throw new IllegalArgumentException("该注解只能用于方法");
	        }
	     
	        msig = (MethodSignature) sig;
	        Object target = pjp.getTarget();
	        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
	        String methodName = currentMethod.getName();
	        //     String methodName = pjp.getSignature().getName(); //获取方法名称  
	        //获取拦截方法的参数
	        String className = pjp.getTarget().getClass().getName();
//	        String ip = HttpKit.getIp();
	        String ip = HttpKit.getIpAddress();
	        Class<?> clazz = Class.forName(className);    
	        String clazzName = clazz.getName();    
	        Object[] args = pjp.getArgs();//参数  
	        //获取参数名称和值  
	        Map<String,Object > nameAndArgs = getFieldsName(this.getClass(), clazzName, methodName,args);   
	        String params = nameAndArgs.toString();
	      
	        User user = ShiroKit.getCurrentUser();
	        LogAop annotation = currentMethod.getAnnotation(LogAop.class);
	        String action = annotation.action();
	        String name = annotation.remark();//日志具体内容
    		//操作的日志
    	     if(user == null){
 	        	LOG.warn("user 信息为空 不记录日志");
 	        }else{
 	        	if (action.indexOf("登录") != -1 ){
 	        		//登录的日志
 	        		LoginLog loginLog = new LoginLog();
 	        		loginLog.setName(name);
 		    	 	loginLog.setIp(ip);
 	 	        	loginLog.setClassName(className);
 	 	        	loginLog.setMethodName(methodName);
 	 	        	loginLog.setParams(params);
 	 	        	loginLog.setResTime(resTime);
 	 	        	loginLog.setUser(user);
 	 	        	Result result = (Result) ob;
 	 	        	if(result.getCode()==1){
 	 	        		loginLog.setSucceed("成功");
 	 	        	}else{
 	 	        		loginLog.setSucceed("失败");
 	 	        	}
 	 	        	loginLog.setMessage(result.getMsg());
 	 	        	loginLogService.save(loginLog);
 	        	}else{
 	        	//开始记录日志
 	        		//操作日志的权限是否打开
 	        		 if(swordConfig.isOperationLog()){
 	        			OperationLog operationLog = new OperationLog();
 	    				operationLog.setName(name);
	        			operationLog.setIp(ip);
	        			operationLog.setClassName(className);
	        			operationLog.setMethodName(methodName);
	        			operationLog.setParams(params);
	        			operationLog.setResTime(resTime);
	        			operationLog.setUser(user);
 	        			if (action.indexOf("删除") != -1 ){
 	    	        		//操作日志
 	        				operationLog.setType("删除");
 	    	 	        	Result result = (Result) ob;
 	    	 	        	if(result.getCode()==1){
 	    	 	        		operationLog.setSucceed("成功");
 	    	 	        	}else{
 	    	 	        		operationLog.setSucceed("失败");
 	    	 	        	}
 	    	 	        	operationLog.setMessage(result.getMsg());
 	    	        	}else if(action.indexOf("查询") != -1){
 	        				operationLog.setType("查询");
 	    	 	            operationLog.setSucceed("成功");
 	    	 	            operationLog.setMessage( ob.toString());
 	    	        	}else if(action.indexOf("新增")!= -1){
 	    	        		operationLog.setType("新增");
 	    	 	        	Result result = (Result) ob;
 	    	 	        	if(result.getCode()==1){
 	    	 	        		operationLog.setSucceed("成功");
 	    	 	        		operationLog.setMessage(result.getData().toString());
 	    	 	        	}else{
 	    	 	        		operationLog.setSucceed("失败");
 	    	 	        		operationLog.setMessage(result.getMsg());
 	    	 	        	}
 	    	        	}else if(action.indexOf("修改")!= -1){
 	    	        		//修改日志  记录变更哪些内容
 	    	        		operationLog.setType("修改");
 	    	 	        	Result result = (Result) ob;
  	    	 	        	if(result.getCode()==1){
 	    	 	        		operationLog.setSucceed("成功");
 	    	 	        		operationLog.setMessage(result.getData().toString());
 	    	 	        	}else{
 	    	 	        		operationLog.setSucceed("失败");
 	    	 	        		operationLog.setMessage(result.getMsg());
 	    	 	        	}
 	    	        	}else if(action.indexOf("上传文件")!= -1){
 	    	        		operationLog.setType("上传文件");
 	    	 	        	Result result = (Result) ob;
 	    	 	        	if(result.getCode()==1){
 	    	 	        		operationLog.setSucceed("成功");
 	    	 	 	        	operationLog.setMessage(result.getData().toString());
 	    	 	        	}else{
 	    	 	        		operationLog.setSucceed("失败");
 	    	 	        		operationLog.setMessage(result.getMsg());
 	    	 	        	}
 	    	        	}else{
 	    	        		operationLog.setType(action);
 	    	 	        	Result result = (Result) ob;
 	    	 	        	if(result.getCode()==1){
 	    	 	        		operationLog.setSucceed("成功");
 	    	 	        		operationLog.setMessage(result.getData().toString());
 	    	 	        	}else{
 	    	 	        		operationLog.setSucceed("失败");
 	    	 	        		operationLog.setMessage(result.getMsg());
 	    	 	        	}
 	    	        	}
 	        		 	operationLogService.save(operationLog);
 	        		 }
 	       
 	        	}
 	        }
	   
	        return ob;
	    }
	    
	    /**
	     * 记录操作日志
	     */
	    @After("logPointCut()")  // 使用上面定义的切入点
	    public void recordLog(JoinPoint joinPoint){
	        Long start = System.currentTimeMillis();
	        Long end = System.currentTimeMillis();
	        LOG.info("记录日志消耗时间:"+ (end - start) / 1000);
	    }
	    
	  //异常通知：拦截service层的异常
		@AfterThrowing(value="logPointCut()",throwing="ex")
		public void afterThrowing(JoinPoint joinPoint,Exception ex) throws Exception{
			System.out.println("抛出异常通知-----------------------------");
			System.out.println("异常代码:\t"+ex.getClass().getName());
			System.out.println("异常信息：\t"+ex.getMessage());
			System.out.println("异常方法：\t"+joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()+"()");
			System.out.println("方法描述：\t"+getServiceMthodDescription(joinPoint));
			System.out.println("异常通知结束----------------------------------");
		}

		
		//获取注解中用于描述的信息 用于service层
		public static String getServiceMthodDescription(JoinPoint joinPoint) throws ClassNotFoundException{
			String targetName=joinPoint.getTarget().getClass().getName();
			String methodName=joinPoint.getSignature().getName();
			Object[] arguments=joinPoint.getArgs();
			Class<?> targetClass=Class.forName(targetName);
			Method[] methods=targetClass.getMethods();
			String name="";
			for(Method method:methods){
				if(method.getName().equals(methodName)){
					Class<?>[] clazzs=method.getParameterTypes();
					if(clazzs.length==arguments.length){
						name=method.getAnnotation(LogAop.class).action();
						break;
					}
				}
			}
			return name;
		}
		
		/**
		 * 获取controller中用于描述的信息 用于controller层
		 * @param joinPoint
		 * @return
		 * @throws Exception
		 */
		public static String getControllerMethodDesciption(JoinPoint joinPoint)throws Exception{
			String targetName=joinPoint.getTarget().getClass().getName();//获取类名
			String methodName=joinPoint.getSignature().getName();//获取方法名称
			Object[] arguments=joinPoint.getArgs();//获取所有参数
			Class<?> targetClass=Class.forName(targetName);
			Method[] methos=targetClass.getMethods();
			String name="";
			for (Method method : methos) {
				if(method.getName().equals(methodName)){
					Class<?>[] clazzs=method.getParameterTypes();
					if(clazzs.length==arguments.length){
						name=method.getAnnotation(LogAop.class).action();
						break;
					}
				}
			}
			return name;
		}
		
		/**
		 * 获取参数名和参数值
		 * 方法功能说明
		 * 创建时间 2017年9月1日 下午11:37:16
		 * 开发者 david
		 * @参数： @param cls
		 * @参数： @param clazzName
		 * @参数： @param methodName
		 * @参数： @param args
		 * @参数： @return
		 * @参数： @throws NotFoundException	
		 * @return： Map<String,Object>
		 */
		private Map<String,Object> getFieldsName(Class<?> cls, String clazzName, String methodName, Object[] args) throws NotFoundException {   
	        Map<String,Object > map=new HashMap<String,Object>();  
	          
	        ClassPool pool = ClassPool.getDefault();    
	        //ClassClassPath classPath = new ClassClassPath(this.getClass());    
	        ClassClassPath classPath = new ClassClassPath(cls);    
	        pool.insertClassPath(classPath);    
	            
	        CtClass cc = pool.get(clazzName);    
	        CtMethod cm = cc.getDeclaredMethod(methodName);    
	        MethodInfo methodInfo = cm.getMethodInfo();  
	        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();    
	        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);    
	        if (attr == null) {    
	            // exception    
	        }    
	       // String[] paramNames = new String[cm.getParameterTypes().length];    
	        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;    
	        for (int i = 0; i < cm.getParameterTypes().length; i++){    
	            map.put( attr.variableName(i + pos),args[i]);//paramNames即参数名    
	        }    
	          
	        //Map<>  
	        return map;    
	    }    

}
