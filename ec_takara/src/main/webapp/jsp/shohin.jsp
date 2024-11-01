<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.io.File, java.io.FilenameFilter"%>
<%@ page
	import="java.io.File, java.io.FilenameFilter, java.util.List, java.util.ArrayList, java.util.Arrays"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品TOP</title>
<!-- CSSファイルを読み込む -->
<link href="/ec_takara/jsp/Style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<form action="ShouhinToShousaiController" method="POST" id="cartForm">
		<div class="container">
			<center>
				<h1>商品TOP</h1>
			</center>

			<table>
				<tr>
					<td>
						<p>
							<a href="/ec_takara/ToListController?pos=${pb.previous }"
								class="nav-link">＜＜</a>
						</p>
					</td>
					<td></td>
					<td></td>
					<td>
					<td>
						<p>
							<a href="/ec_takara/ToListController?pos=${pb.next }"
								class="nav-link">＞＞</a>
						</p>
					</td>
				</tr>
				<!-- 商品を5件ごとに新しい行に分ける -->
				<c:forEach var="goodtableitem" items="${goodbeanlist}"
					varStatus="status">
					<!-- 5件ごとに新しい行を開始 -->
					<c:if test="${status.index % 5 == 0}">
						<tr>
					</c:if>
					<td>
						<%
						// 画像フォルダのパスを指定（サーバー内の絶対パス）
						String folderPath = application.getRealPath("/images");
						java.io.File folder = new java.io.File(folderPath);
						java.io.File[] listOfFiles = folder
								.listFiles((dir, name) -> name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpg"));

						// 選ばれた画像を追跡するためのリスト
						List<String> selectedImages = (List<String>) session.getAttribute("selectedImages");
						if (selectedImages == null) {
							selectedImages = new ArrayList<>();
						}

						// selectedImagesのコピーを作成（ラムダ式で使用するため）
						List<String> selectedImagesCopy = new ArrayList<>(selectedImages);

						// 画像ファイルが存在する場合、ランダムに選択して重複しないようにする
						String randomImagePath = "";
						if (listOfFiles != null && listOfFiles.length > 0) {
							List<java.io.File> availableFiles = new ArrayList<>(Arrays.asList(listOfFiles));

							// 既に選ばれた画像をリストから除外
							availableFiles.removeIf(file -> selectedImagesCopy.contains(file.getName()));

							// 画像がまだ選べる場合
							if (!availableFiles.isEmpty()) {
								int randomIndex = (int) (Math.random() * availableFiles.size());
								java.io.File selectedFile = availableFiles.get(randomIndex);
								randomImagePath = "/ec_takara/images/" + selectedFile.getName();

								// 選ばれた画像をリストに追加
								selectedImages.add(selectedFile.getName());
							} else {
								// すべての画像が選ばれた場合、リストをクリアして再度選択できるようにする
								selectedImages.clear();
							}
						}

						// セッションに選ばれた画像のリストを保存
						session.setAttribute("selectedImages", selectedImages);
						%>
						 <img src="<%=randomImagePath%>" alt="本たち" class="product-image"><br>
						<span class="product-name"><c:out
								value="${goodtableitem.goods_name}" /></span><br> <span
						class="price">￥<fmt:formatNumber value="${goodtableitem.price}" pattern="#,##0"/>
										<!--<c:out value="${goodtableitem.price}" />--></span><br>
						<input type="hidden" name="gname"
						value="${goodtableitem.goods_name}"> <input type="hidden"
						name="price" value="${goodtableitem.price}"> <input
						type="hidden" name="writer" value="${goodtableitem.writer}">
						<input type="hidden" name="book_kainds"
						value="${goodtableitem.book_kinds}"> <input type="hidden"
						name="cname" value="${goodtableitem.cname}"> <input
						type="hidden" name="goods_id" value="${goodtableitem.goods_id}">
						<input type="hidden" name="quantity"
						id="quantity_${goodtableitem.goods_id}"> <a
						href="/ec_takara/ShouhinToShousaiController?goods_id=${goodtableitem.goods_id}">商品見る</a>
					</td>

					<!-- 次の行に進む前に行を閉じる -->
					<c:if test="${(status.index + 1) % 5 == 0}">
						</tr>
					</c:if>
				</c:forEach>

				<!-- 残った商品がある場合、行を閉じる -->
				<c:if test="${goodbeanlist.size() % 5 != 0}">
					</tr>
				</c:if>
			</table>

			<center>
				<a href="/ec_takara/ShousaiToCart">カート</a>
			</center>
			<div class="logout">
				<a href="/ec_takara/LogoutController">ログアウト</a>
			</div>
			<div class="footer">&copy; 2024 takaradayo</div>
		</div>
	</form>
</body>
</html>

