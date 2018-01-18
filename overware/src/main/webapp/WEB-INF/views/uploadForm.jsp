<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>


</head>
<body>
	<form id="form1" action="uploadForm" method="post" enctype="multipart/form-data" target="zeroFrame">
		<input type="file" name="file">
		<input type="submit">
	</form>
	<iframe name="zeroFrame"></iframe>
	<script>
	function addFilePath(msg){
		alert(msg+"가 업로드 되었습니다.");
		document.getElementById("form1").reset();
	}
</script>
</body>
</html>