package com.overware.spring;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@RequestMapping(value="/uploadForm",method=RequestMethod.GET)
	//로그인 폼 불러오기 
	public void loadForm() {
		
	}
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@RequestMapping(value="/uploadForm",method=RequestMethod.POST)
	public String uploadForm(MultipartFile file, Model model) throws Exception{
		//MultiPart 객체를 사용하게 되면 파일 이름이나 사이즈 파일의 MIME 타입등을 알수 있다
		logger.info("orginalName:" +file.getOriginalFilename());
		logger.info("size:" + file.getSize());
		logger.info("contentType:" + file.getContentType());
		logger.info("byte:"+file.getBytes());
		
		//기존파일명을 가지고 uuid를 이용해서 새로운 파일을 만들고  해당파을의 byte형의 데이터를 copy해서 target형식으로 바꾸어서 하나를 더 카피해놓음 
		String savedName=uploadFile(file.getOriginalFilename(),file.getBytes());
		
		model.addAttribute("savedName", savedName);
		
		return "uploadResult";
	}
	
	private String uploadFile(String orginalName,byte[] fileData) throws Exception{
		
		UUID uid=UUID.randomUUID(); //파일의 고유한 키값을 설정하는데 사용
		String savedName=uid.toString()+"_"+orginalName;  //savedName 에는 고유한 키값에 + 기존 파일명을 붙여서 담는다.
		File target= new File(uploadPath,savedName); //uploadPath: 파일이 있는 폴더 경로, savedname:실제 파일  을 담아서File객체를 생성한다.   
		FileCopyUtils.copy(fileData, target); //파일을 하나 복사해놓는다고 생각하면 된다. 즉 바이트형식으로 온 데이터를 target형식 즉 파일형식으로 카피해 놓는다는 의미 
		return savedName;
	}
}
