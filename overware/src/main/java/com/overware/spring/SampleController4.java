package com.overware.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4 {

	private static final Logger logger=LoggerFactory.getLogger(SampleController4.class);
	
	@RequestMapping("/doE")
	// RedirctAttributes: doE함수를  실행할때에 데이터를 임시로 추가해서 넘기는 작업이 가능 , 즉 redirect 할때 데이터를 추가해서 호출되는 곳에 넘길수 있다는 의미
	public String doE(RedirectAttributes rttr) {
		
		logger.info("redirct 처리로 doE가 아닌 doF가 호출됨");
		//addFlashAttribute: 임시 데이터를 전달하는 데 사용
		rttr.addFlashAttribute("msg","this is msg and redirect practice");
		
		return"redirect:/doF";
	}
	//deE를 실행하게 되면 doF가 호출됨
	@RequestMapping("/doF")
	public void doF(@ModelAttribute("msg") String msg) {
		logger.info("redirect 처리해서 doF를 불러옴 " +msg);
	}
}
