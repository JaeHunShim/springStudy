// 1. 확장자를 통해서 이미지인지 일반 파일인지 체그하기위한 함수
function checkImageType(fileName){
	
	var pattern =/jpg|png|jpeg|gif/i;
	
	return fileName.match(pattern); // 맞는 패턴을 찾아서 fileName에 추가 
}
//2. 파일의 정보 (즉 AJax를 통해서 보낼 데이터에 대한 내용)
function getFileInfo(fullName){
	//파일 이름, 이미지가 있는 경로, 화면에서 원본 파일을 볼수 있는 링크를 위해서사용 
	var fileName,imgsrc,getLink;
	//최종 적용될 파일 경로 (3가지중에  조건에 맞는걸 fileLink에 넣겠지
	var fileLink;
	
	if(checkImageType(fullName)){
		imgsrc="/displayFile?fileName="+fullName;
		//14번째 부터 UUID로 생성한 파일명부터 이니까 
		fileLink=fullName.substr(14);
		//썸데일 s_를 제외시키기 위해서 사용
		var front= fullName.substr(0,12);//폴더(년/월/일)
		var end=fullName.substr(14);//썸네일일 경우는 "s_"제외하고  그후의 UUID만 
		
		getLink="/displayFile?fileName="+front+end;
	
	}else{ //일반 파일일 경우 
		imgsrc="/resources/dist/img/file.jpg";
		fileLink=fullName.substr(12); //원본파일일 경우는 폴더경로만 빼고
		getLink="/displayFile?fileName="+fullName;
	}
	fileName=fileLink.substr(fileLink.indexOf("_")+1);
	
	return {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName}; //register.jsp에서 템플릿(handlebars를 이용한)을 이용해서 화면에 보여지게 하는 데이터
}