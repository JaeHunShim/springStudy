package com.overware.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger= LoggerFactory.getLogger("SampleInterceptor.class");

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
							Object hendler, ModelAndView mav) throws Exception{
		
		logger.info("postHandle=====================");
		
		Object result=mav.getModel().get("result");
		
		if(result !=null) {
			request.getSession().setAttribute("result", result); // result값을 set하고 session에 담아서 서버에 요청처리 하고 doA를 호출 
			response.sendRedirect("/doA");
		}
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception{
		
		logger.info("preHandle==========");
		
		HandlerMethod method= (HandlerMethod)handler; // 현재 실행되는 컨트롤러가 무엇인지
		Method methodObj=method.getMethod(); // 컨트롤러에서 실행되는 메소드
		
		logger.info("Bean :" + method.getBean());
		logger.info("Method :" + methodObj);
		
		return true;
	}
	  
}
