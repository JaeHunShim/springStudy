package com.overware.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController2 {

	private static final Logger logger=LoggerFactory.getLogger(SampleController2.class);
	//@ModelAttribute 해당객체를 바로 view에 전달한다  예로 url에 msg="원하는 것"을 하면 뷰에서 보여지는걸 알수 잇다.
	@RequestMapping("abc")
	public String abc(@ModelAttribute("msg") String msg) {
		logger.info("abc호출");
		
		return "result";
	}
}
