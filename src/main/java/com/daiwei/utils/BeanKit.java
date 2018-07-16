package com.daiwei.utils;


import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;


/**
* 将object对象转换成object对象
* @author  david:
* @date 创建时间：2017年7月22日 下午9:39:11
* @version 1.0
* @parameter
 * @since 
* @return 
*/
public class BeanKit extends BeanUtils {
	
	/** The beanutils2. */
	private static org.apache.commons.beanutils.BeanUtils beanutils2;
	
	private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);
	/**
	 * Populate.
	 *
	 * @param bean the bean
	 * @param properties the properties
	 */
	@SuppressWarnings("static-access")
	public static void populate(Object bean, Map<String, ? extends Object> properties) {
		try {
			beanutils2.populate(bean, properties);
		} catch (IllegalAccessException e) {
			logger.debug("{}",e.getMessage());	
		} catch (InvocationTargetException e) {
			logger.debug("{}",e.getMessage());
		}		
	}

	/**
	 * Describe.
	 *
	 * @param source the source
	 * @param ignoreProperties the ignore properties
	 * @return the map
	 */
	public static Map<String, Object> describe(Object source, String... ignoreProperties) {
		Assert.notNull(source, "Source must not be null");
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
		Class<?> clazz = source.getClass();
		PropertyDescriptor[] sourcePds = BeanUtils.getPropertyDescriptors(clazz);
		for (int i = 0; i < sourcePds.length; i++) {
			PropertyDescriptor sourcePd = sourcePds[i];
			String name = sourcePd.getName();
			if (ignoreList.contains(name)) {
				continue;
			}
			try {
				Method readMethod = sourcePd.getReadMethod();
				if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
					readMethod.setAccessible(true);
				}
				Object value = readMethod.invoke(source, new Object[0]);
				result.put(name, value);
			} catch (Throwable e) {
				logger.debug("Could not copy properties from source to HashMap:{}",e.getMessage());
				//throw new FatalBeanException("Could not copy properties from source to HashMap", e);
			}

		}
		return result;
	}


}

