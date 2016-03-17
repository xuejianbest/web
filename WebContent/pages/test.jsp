<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test</title>
<script src="/test/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#debug").click(function(){
			alert($("#file").val());
		});
		
	})
</script>
</head>
<body>
	<form action="/test/fileform" enctype="multipart/form-data" method="post">
		<input id="file" name="file" type="file"/>
		<input name="text" value="text_AAA中国"/>
		<input type="submit"/>
	</form>
	
	<input type="button" id="debug" value="debug"/>
</body>
</html>