package com.overware.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {

	private static final Logger logger= LoggerFactory.getLogger(UploadFileUtils.class);
	
	public static String uploadFile(String uploadPath, String originalName,byte[] fileData) throws Exception{
		// 1. uuid 객체를 생성하고
		UUID uid = UUID.randomUUID();
		// 2. uuid로 생성한 문자열과  원래 파일명을 합쳐서 savedName에 데 담는다. 즉 이건 파일명을 만들어준것 
		String savedName = uid.toString()+"_"+ originalName;
		// 3. 파일저 저장될 경로 로서 밑에서 함수 생성해놓은것을 가져다쓴거임(년도/월/일 형식으로 경로로 디렉토리를 만들면서(makeDir 사용해서))
		String savedPath = calcPath(uploadPath);
		// 4.원래 파일 경로(d:/image/upload)와 savedPath로 만들어진 폴더의 savedName 파일을 찾아서 파일객체로 생성 
		File target = new File(uploadPath + savedPath,savedName);
		// 5. formData형식으로  데이터가 들어오기때문에 마지막에는 들어온데이터를 파일객체로 만든다. 
		FileCopyUtils.copy(fileData, target); 
		// 6. 원본 파일명에서 확장자자가 이미지인지 아닌지를 판별하기 위해서 .뒤의 확장자를 찾아서 formatName에 담아서  그걸가지고 일반파일인지 이미지 파일인지 구분한다. 
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
		
		String uploadedFileName = null;
		
		// 7. MediaUtils를 이용해서 썸네일 파일 유무를 확인함(makeThumbnail(이미지파일),makeIcon(일반파일))
		if(MediaUtils.getMediaType(formatName) != null) {
			
			uploadedFileName = makeThumbnail(uploadPath,savedPath,savedName);
		}else {
			uploadedFileName = makeIcon(uploadPath,savedPath,savedName);
		}
		//8. 분류된걸 return
		return uploadedFileName;
	}
	//업로드되는 경로를 설정해주는 메소드 
	private static String calcPath(String uploadPath) {
		
		Calendar cal= Calendar.getInstance(); // Calendar객체를 생성하고 
		//여기서부터는 년/월/일로 파일 경로 생성해주는 부분
		String yearPath= File.separator+cal.get(Calendar.YEAR); // 현재 시스템의 년도를 받아와서  separator 메소드로 / 로 경로를 분리해준다
		// '년도/" 다음 시스템 상의 "월"을 받아와서 그 데이터를 십의 자리로 형식화함 ("00")-->이부분 "+1"을 해준 이유는 month같은 경우는 0~11로 되기때문에 +1을 해주어야 한다. 
		String monthPath= yearPath+ File.separator+ new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		// "년도/달/" 다음 시스템 상의 "일"을 받아와서  데이터를 집의 자리로 형식화함 ("00") --> 이부분
		String datePath= monthPath+File.separator+new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		logger.info(datePath);
		
		makeDir(uploadPath,yearPath,monthPath,datePath); // 위의 정보를 가지고 디렉토리를 만든다. 
		return datePath;  // 즉  "년도/달/일" 모든 정보를 담고있는 datePath를 return 
	}
	// 디렉토리 생성하는 메소드
	private static void makeDir(String uploadPath, String ...paths) {
		
		if(new File(uploadPath+paths[paths.length-1]).exists()) {
			
			return;
		}
		for(String path:paths) { //paths 데이터가 있을때까지 반복하는데  반복돌때 값을  변수 path에 대입하고 존재 하지 않다면 계속해서  디렉토리 생성 
			
			File dirPath = new File(uploadPath+path);
			
			if(!dirPath.exists()) {
				
				dirPath.mkdir();
			}
		}	
	}
	//썸네일 파일 생성
	private static String makeThumbnail(String uploadPath,String path,String fileName) throws Exception{
		
		// upload+path(파일폴더)의 fileName 의 파일을 File객체로 생성하고 이걸 이미지 파일로 읽어서 bufferdImage객체로 생성함 즉 파일객체를 buffer에담는다고 쉽게 생각하면될듯
			//즉 메모리상의 이미지를 만든다는 의미 
		BufferedImage sourceImg= ImageIO.read(new File(uploadPath+path,fileName));
		// 원본 파일을 메모리상에 로딩하고 정해진 크기에 맞게 이미지 파일에 원본 이미지를 복사함 
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
		// fileName에 s_를 붙여서 이 파일이 섬네일 파일이란걸 알려준다. 
		String thumbnailName = uploadPath+path+File.separator+"s_"+fileName;
		
		File newFile = new File(thumbnailName);
		
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		// 브라우저에서는 \경로를 읽지 못하기 때문에 /로 replace 해서 리턴해줌 
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	private static String makeIcon(String uploadPath,String path,String fileName) throws Exception{
		
		String iconName = uploadPath+path+File.separator+fileName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
}

