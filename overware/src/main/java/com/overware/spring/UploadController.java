package com.overware.spring;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.overware.util.MediaUtils;
import com.overware.util.UploadFileUtils;

@Controller
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@RequestMapping(value="/uploadForm",method=RequestMethod.GET)
	//로그인 폼 불러오기 
	public void loadForm() {
		
	}
	//1. form 형식으로 파일 업로드  
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@RequestMapping(value="/uploadForm",method=RequestMethod.POST)
	public String uploadForm(MultipartFile file, Model model) throws Exception{
		//MultiPart 객체를 사용하게 되면 파일 이름이나 사이즈 파일의 MIME 타입등을 알수 있다
		logger.info("orginalName:" +file.getOriginalFilename());
		logger.info("size:" + file.getSize());
		logger.info("contentType:" + file.getContentType());
		logger.info("byte:"+file.getBytes());
		
		// 기존파일을 uuid를 이용해서 고유한 파일명을 만들고 파일 경로와 새로 생성한 파일명을 파일객체 로 만들어서 파일 데이터를 파일로 처리하게끔 한다. 
		String savedName=uploadFile(file.getOriginalFilename(),file.getBytes());
		
		model.addAttribute("savedName", savedName);
		
		return "uploadResult";
	}
	// 저장되는 파일에 대한 정보를  따로 함수로 생성해서 저장 
	private String uploadFile(String orginalName,byte[] fileData) throws Exception{
		
		UUID uid=UUID.randomUUID(); //파일의 고유한 키값을 설정하는데 사용
		String savedName=uid.toString()+"_"+orginalName;  //savedName 에는 고유한 키값에 + 기존 파일명을 붙여서 담는다.
		File target= new File(uploadPath,savedName); //uploadPath: 파일이 있는 폴더 경로, savedname:실제 파일  을 담아서File객체를 생성한다.   
		FileCopyUtils.copy(fileData, target); //파일을 하나 복사해놓는다고 생각하면 된다. 즉 바이트형식으로 온 데이터를 target형식 즉 파일형식으로 카피해 놓는다는 의미 
		return savedName;
	}
	@RequestMapping(value="/uploadAjax",method=RequestMethod.GET)
	public void uploadAjax() {
		
	}
	//Ajax로 데이터를 받아오기때문에 ResponseBody로 처리 
	@ResponseBody
	@RequestMapping(value="/uploadAjax",method=RequestMethod.POST,produces="text/plain;charset=utf-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception{
		
		logger.info("originalName"+ file.getOriginalFilename());
		logger.info("size"+ file.getSize());
		logger.info("contentType"+file.getContentType());
		
		/*return new ResponseEntity<String>(file.getOriginalFilename(),HttpStatus.OK);*/
		return new ResponseEntity<String>(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()),HttpStatus.OK);
		
	}
	//파일을 화면에 출력
	@ResponseBody
	@RequestMapping(value="/displayFile", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		
		InputStream in = null;
		ResponseEntity<byte[]> entity= null;
		
		logger.info("File Name : " +fileName);
		
		try {
			String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
			//mType 에는 확장자에 대한 데이터를 자기 욌음 
			MediaType mType= MediaUtils.getMediaType(formatName);
			
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(uploadPath+fileName);
			
			if(mType != null) {//이미지 파일일 경우 
				headers.setContentType(mType);
			}else {// 그냥 파일일 경우
				//원본 파일명 (uuid로 생성한 부분을 제외하고)
				fileName= fileName.substring(fileName.indexOf("_")+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				//한글 처리 
				headers.add("Content-Disposition", "attachment;fileName=\""+new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");
			}
			//실제로  파일에서 데이터를 읽어오는 부분 (IOUtils.toByeArray)  바이트 형의의 데이터를 읽어오는 역할 
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);
			
		}catch(Exception e) {
			e.printStackTrace();
			entity= new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
		return entity;
	}
	
}
