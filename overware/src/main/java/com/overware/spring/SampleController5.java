package com.overware.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.overware.domain.ProductVO;

@Controller
public class SampleController5 {

	private static final Logger logger=LoggerFactory.getLogger(SampleController.class);
	//ResponseBody: 데이터를 Json형태로 리턴해준다.view에 
	@RequestMapping("/doJson")
	public @ResponseBody ProductVO doJson() {
		
		ProductVO vo = new ProductVO("재훈", 100000);
		logger.info("Json으로 가는값은------"+vo);
		return vo;
	}
}
