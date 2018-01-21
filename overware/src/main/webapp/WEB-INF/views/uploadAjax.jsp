<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
.fileDrop{
	width:100%;
	height:200px;
	border:1px dotted blue;
}
small{
	margin-left:3px;
	font-weight:bold;
	color:gray;
}
</style>
</head>
<body>
	<h3>Ajax File Upload</h3>
	<div class="fileDrop"></div>
	
	<div class="uploadedList"></div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>

	$(".fileDrop").on("dragenter dragover",function(event){
		event.preventDefault();
	});
	$(".fileDrop").on("drop",function(event){
		event.preventDefault();
		
	 	var files=event.originalEvent.dataTransfer.files;
		
		var file=files[0];
		
		console.log(file);
		
		var formData=new FormData();
		
		formData.append("file",file); // 이렇게 만들면 이걸 ajax로 보내기만 하면됨
		 
		$.ajax({
			type:"post",
			url:"/uploadAjax",
			dataType:"text",
			data:formData,
			contentType:false,
			processData:false,
			success:function(data){
				
				var str="";
				
				console.log(data);
				console.log(checkImageType(data));
				console.log(getImageLink(data));
				
				if(checkImageType(data)){
					str="<div>"
						+"<a href='displayFile?fileName="+getImageLink(data)+"'>"
						+"<img src='displayFile?fileName="+data+"'/></a>"
						+getImageLink(data)
						+"<small data-src="+data+">X</small></div>";		
				}else{
					str="<div>"
						/* +data+ */
						+getOriginalName(data)
						+"<small data-src="+data+">X</small></div>";
				}
				// upladedList 클래스태그 뒤에 생성한 str을 추가함 
				$(".uploadedList").append(str);
			}
		});
	});
	//뎅터를 받아오고 filname이 어떠한 확장자 파일인지  검사하는 부분
	function checkImageType(fileName){
		
		var pattern=/jpg$|gif$|png$|jpeg$/i; //i 는 대소문자구별없이라는 의미 
		
		return fileName.match(pattern);
	}
	//파일명이 너무길어서 원본파일명만 출력하게 하는 함수 
	function getOriginalName(fileName){
		//이미지 파일이면  파일이름의 앞부분 0부터 시작해서 12까지는 년/월/일을 추출하고 substr(14)는 섬네일파일인 s_를 추출하는 부분 
		if(checkImageType(fileName)){
			
			return;
		}
		//"_"다음부터가 원본파일명이니까 +1을 해주고, 파일의 인덱스값을 idx에 담아서 subString으로 해당 인덱스의 문자열을 받는다
		var idx = fileName.indexOf("_")+1;  
		
		return fileName.substr(idx);
	}
	//이미지 파일이름 가지고 오기(원본 파일명)
	function getImageLink(fileName){
		
		if(!checkImageType(fileName)){
			return;
		}
		//0~12까지 추출 한 문자열과, 14~끝까지 추출한 문자열을 서로 더해서 보여주게 된다. 즉 "s_이거만 제거하고 "
		var front = fileName.substr(0,12);
		var end= fileName.substr(14);
		
		return front+end;
	}
	//파일 삭제 처리 
 $(document).ready(function(){
	

	$(".uploadedList").on("click","small",function(event){
		
		var that=(this);
		
		$.ajax({
			url:"deleteFile",
			type:"post",
			data:{fileName:$(this).attr("data-src")},
			dataType:"text",
			success:function(result){
				if(result=="deleted"){
					alert("삭제되었습니다.");
				}
			}
		});
	});
});
</script>
</html>