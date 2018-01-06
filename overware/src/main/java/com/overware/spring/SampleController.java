package com.overware.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

	private static final Logger logger=LoggerFactory.getLogger(SampleController.class);
	// 리턴타입이 void이기 때문에 해당페이지를 호출하게 된다.
	@RequestMapping("doA")
	public void doA() {
		logger.info("doa 호출");
	}
	@RequestMapping("ex")
	public void ex() {
		logger.info("ex 호출");
	}
}
