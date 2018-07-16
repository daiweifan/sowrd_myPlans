/**
 * 
 */
package com.daiwei.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.daiwei.utils.Utils;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 模板生成器
 * @author david
 *
 */
public class CreateFreeMarker {

	private static Configuration configuration;
	private static Template template;
	private static Writer writer;

	// bean 
	public static final String BEAN_URL = "com.daiwei.project.backend.system";
	public static final String ANNOTATION_AUTHOR_NAME = "david";
	public static final String ANNOTATION_AUTHOR_MAIL = "daiwei_fan@163.com";
	public static final String ANNOTATION_VERSION = "1.0";

	// date formate
	public static final String DATE_FROMATE = "yyyy-MM-dd HH:MM:ss";

	public static void main(String[] args) throws Exception {
		Bean bean = new Bean();
		bean.setName(Utils.toUpperCaseFirstOne("department"));
		bean.setLowerName("department");
		bean.setContent("部门");
		bean.setTableName("s_department");
		bean.setPackageUrl(BEAN_URL);
		bean.setAuthorMail(ANNOTATION_AUTHOR_MAIL);
		bean.setAuthorName(ANNOTATION_AUTHOR_NAME);
		bean.setVersion(ANNOTATION_VERSION);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FROMATE);
		bean.setDate(simpleDateFormat.format(new Date()));
		createJavaFile(bean, "model");
		createJavaFile(bean, "dao");
		createJavaFile(bean, "service");
		createJavaFile(bean, "controller");
	}

	/**
	 * 生成java文件
	 * 利用freemarker生成自定义的javaBean
	 * 
	 * @param bean
	 *            参数配置
	 * @param type
	 *            使用的模板文件
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static void createJavaFile(Bean bean, String type) throws Exception {
		System.out.println("baseUrl:"+System .getProperty("user.dir"));
		String baseUrl = System .getProperty("user.dir");
		// 创建Freemarker配置实例
		configuration = new Configuration();
		configuration.setDirectoryForTemplateLoading(new File(baseUrl+"/src/main/resources/swordTemplates"));

		// 创建数据模型
		Map<String, Object> root = new HashMap<String, Object>();

		root.put("bean", bean);

		// 加载模板文件
		template = configuration.getTemplate(type+".java.ftl");

		String beanPath = System.getProperty("user.dir") + "/src/main/java/" + bean.getPackageUrl().replace(".", "/") +"/"+type+ "/";
		System.out.println("beanPath:"+beanPath);
		File filePath = new File(beanPath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}

		String suffix = convertSuffix(type);
		String filePathOfBean = beanPath + "/"+bean.getName()+suffix;
		
		File file = new File(filePathOfBean);
		if (!file.exists()) {
			file.createNewFile();
		}

		// 显示生成的数据
		writer = new FileWriter(file);
		template.process(root, writer);
		writer.flush();
		writer.close();
	}
	
	/**
	 * 生成页面文件
	 * @param bean
	 * @param type
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static void createHtmlFile(Bean bean, String type) throws Exception {
		System.out.println("baseUrl:"+System .getProperty("user.dir"));
		String baseUrl = System .getProperty("user.dir");
		// 创建Freemarker配置实例
		configuration = new Configuration();
		configuration.setDirectoryForTemplateLoading(new File(baseUrl+"/src/main/resources/swordTemplates"));

		// 创建数据模型
		Map<String, Object> root = new HashMap<String, Object>();

		root.put("bean", bean);

		// 加载模板文件
		template = configuration.getTemplate(type+".html.ftl");

		String beanPath = System.getProperty("user.dir") + "/src/main/resources/templates/" + bean.getPackageUrl().replace(".", "/") +"/"+type+ "/";
		System.out.println("beanPath:"+beanPath);
		File filePath = new File(beanPath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}

		String suffix = ".ftl";
		String filePathOfBean = beanPath + "/"+bean.getName()+suffix;
		
		File file = new File(filePathOfBean);
		if (!file.exists()) {
			file.createNewFile();
		}

		// 显示生成的数据
		writer = new FileWriter(file);
		template.process(root, writer);
		writer.flush();
		writer.close();
	}
	
	//转换后缀
	public static String convertSuffix(String type){
		String suffix = "";
		switch (type) {
		case "model":
			suffix = ".java";
			break;
		case "dao":
			suffix = "Dao.java";
			break;
		case "service":
			suffix = "Service.java";
			break;
		case "controller":
			suffix = "Controller.java";
			break;
		}
		return suffix;
	}
}
