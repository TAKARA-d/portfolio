<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Context-type" content="text/html; charset=UTF-8">
<title>ソートページ</title>
</head>
<body>
<style>
	#population{text-align:right}
	#search{text-align:right}
</style>
</head>
<body>
	<p>bought Table List</p>
	<div id="serch">
		<p><a href="OperationPageController?pos=1">一覧に戻る</a></p>
	</div>
	<table border="1" align="center">
	<jsp:include page="bought2.jsp"></jsp:include>
	</table>
</body>
</html>