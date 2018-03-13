<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/resources/plugins/jQueryUI/jquery-ui-1.10.3.min.js"></script>
<title>Spring AutoCompleate</title>
<script>
$(function() {      
    $("#tagsName").autocomplete({
        source: function (request, response) {
            $.getJSON("${pageContext.request.contextPath}/getMachedNames.web", {
                term: request.term
            }, response);
        }
    });
});
</script>
</head>
<body>
<form method="get" action="">
    <h1>${message}</h1>
    Enter Name: <input id="tagsName">
    </form>    
</body>
</html>