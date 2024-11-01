<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Context-type" content="text/html; charset=UTF-8">
<title>会員登録画面</title>
<!-- CSSファイルを読み込む -->
<link href="/ec_takara/jsp/Style.css" rel="stylesheet" type="text/css">
</head>
<body>
<center><h1>新規会員登録</h1></center></br>
<center><h3>※空白での登録は許しまへん!!</h3></center>
<form action="/ec_takara/MemberController" method="POST">
<center>
	<table>
	<tr>
		<td>ログインID</td>
		<td><input type="text" size=50 name="user_id"></td>
	</tr>
	<tr>
		<td>パスワード</td>
		<td><input type="password" size=50 name="password"></td>
	</tr>
	<tr>
		<td>名前</td>
		<td><input type="text" size=20 name="mname" placeholder="苗字"><br><br>
		<input type="text" size=20 name="sname" placeholder="名前"></td>
	</tr>
	<tr>
		<td>性別</td>
		<td><input type="radio" name="sex" value="男">男
			<input type="radio" name="sex" value="女">女
		</td>
	</tr>
	<tr>
		<td>住所</td>
		<td><input type="text" size=80 name="address"></td>
	</tr>
	<tr>
			<td>生年月日</td>
			<td><select name="yyyy">
				<c:forEach var="y" begin="1930" end="2023" step="1">
				<option value="${y }"><c:out value="${y }"/></option>
				</c:forEach>
				</select>年
				<select name="mm">
				<c:forEach var="m" begin="1" end="12" step="1">
				<option value="${m }"><c:out value="${m }"/></option>
				</c:forEach>
				</select>月
				<select name="dd">
				<c:forEach var="d" begin="1" end="31" step="1">
				<option value="${d }"><c:out value="${d }"/></option>
				</c:forEach>
			</select> <span class="label">日</span>
			</td>
			</tr>
	</table>
<center>
	<tr>
		<input type="hidden" name="pos" value="1"/>
		<td><input type="submit" value="登録確認へ"/></td>
	</tr>
	<c:if test="${misserror}">
    <p>ログインIDの重複が発生しました。</p>
</c:if>
<c:if test="${emptyerror}">
    <p>入力しろ</p>
</c:if>
</center>
</form>
<a href="/ec_takara/jsp/login.jsp">戻る</a>
</body>
</html>