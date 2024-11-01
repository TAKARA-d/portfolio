<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Context-type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	#population{text-align:right}
	#search{text-align:right}
</style>
<!-- CSSファイルを読み込む -->
<link href="/ec_takara/jsp/Style.css" rel="stylesheet" type="text/css">
</head>
<body>
<p>bought Table List</p>
<div id="search">
	<form action="OperationTototalController" method="POST">
		<p>検索リスト</p><br />
		<table>
		<input type="radio" name="sex" value="null" checked="checked"/>未選択
		<input type="radio" name="sex" value="男" />男
		<input type="radio" name="sex" value="女"/>女
		</table>
		<table>
			<select name="era">
				<option value="null" selected="selected">未選択</option>
				<option value="未成年" >未成年</option>
				<option value="10代">10代</option>
				<option value="20代">20代</option>
				<option value="30代">30代</option>
				<option value="40代">40代</option>
				<option value="50代">50代</option>
				<option value="60代">60代</option>
				<option value="70代以上">70代以上</option>
			</select>
		</table>
		<table>
			<select name="book_kinds">
				<option value="null" selected="selected">未選択</option>
				<option value="エッセイ" >エッセイ</option>
				<option value="絵本">絵本</option>
				<option value="音楽">音楽</option>
				<option value="教育">教育</option>
				<option value="語学">語学</option>
				<option value="コミック">コミック</option>
				<option value="資格">資格</option>
				<option value="写真">写真</option>
				<option value="図鑑">図鑑</option>
				<option value="スポーツ">スポーツ</option>
				<option value="ノンフィクション">ノンフィクション</option>
				<option value="ビジネス">ビジネス</option>
				<option value="美容">美容</option>
				<option value="ファンタジー">ファンタジー</option>
				<option value="ホラー">ホラー</option>
				<option value="ミステリー">ミステリー</option>
				<option value="旅行">旅行</option>
				<option value="料理">料理</option>
				<option value="歴史">歴史</option>
				<option value="恋愛">恋愛</option>				
			</select>
		</table>
		<br></br>
		<input type="hidden" value="1" name="pos"/>
		<input type="submit" value="検索"/>
	</form>
	<a href="/ec_takara/LogoutController">ログアウト</a>
</div>
<table border="1" align="center">
	<jsp:include page="bought.jsp"></jsp:include>
	<tr>
		<td>
			<p><a href="OperationPageController?pos=${pb2.previous }">＜＜</a></p>
		</td>
		<td></td><td></td><td></td>
		<td>
			<p><a href="OperationPageController?pos=${pb2.next }">＞＞</a></p>
		</td>
	</tr>
</table>
</body>
</html>