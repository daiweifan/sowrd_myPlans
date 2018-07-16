package com.daiwei.common.xss;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.daiwei.common.support.HttpKit;

/**
 * xss过滤器
* @ClassName: XssFilter 
* @Description: TODO
* @author daiwei
* @date 2018年6月1日 下午3:29:06 
* 
* @version V2.0
 */
@WebFilter(filterName = "xssFilter", urlPatterns = "/*", asyncSupported = true)
public class XssFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
		chain.doFilter(HttpKit.getRequest(), response);
	}

	@Override
	public void destroy() {
	}

}