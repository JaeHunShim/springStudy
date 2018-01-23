package com.overware.spring;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleController {

	private static final Logger logger=LoggerFactory.getLogger(SampleController.class);
	// 리턴타입이 void이기 때문에 해당페이지를 호출하게 된다.
	@RequestMapping(value="/doA", method=RequestMethod.GET)
	public String doA(Locale locale,Model model) {
		
		logger.info("doA 호출-----------------------------------------");
		return "home";
	}
	@RequestMapping(value="/doB",method=RequestMethod.GET)
	public String doB(Locale locale, Model model) {
		
		logger.info("doB 호출 -----------------------------------------");
		model.addAttribute("result","dob result"); //home.jsp 에서 jstl형식으로 불러옴 결과는 dob result가 호출된다 
		
		return "home";
	}
	@RequestMapping("ex")
	public String ex() {
		logger.info("ex 호출");
		
		return "home";
	}
}
