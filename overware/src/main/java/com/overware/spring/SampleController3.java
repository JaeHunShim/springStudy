package com.overware.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.overware.domain.ProductVO;

@Controller
public class SampleController3 {
	
	private static final Logger logger=LoggerFactory.getLogger(SampleController3.class);
	
	@RequestMapping("/doD")
	//Model: 뷰에 원하는 데이터를 전달하는 일종이 "컨테이너" 역할이라고 할수 있다 즉 model에 담아서 view에 전달
	public String doD(Model model) {
		
		ProductVO vo=new ProductVO("jeahun",100000); //객체를 생성해서 바로 파라미터에 데이터를 집어넣고 모델에 추가한다.
		logger.info("doD");
		model.addAttribute("vo",vo); //이러면 vo.name, vo.pirce 이런식으로 view에서 사용하면 된다. 앞에 "vo"로 하면 view에서 바로 이 이름으로 사용할수 있다. 
		
		return "ProductDetail";
	}
}
