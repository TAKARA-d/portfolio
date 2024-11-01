<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品詳細</title>
<!-- CSSファイルを読み込む -->
    <link href="/ec_takara/jsp/Style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="container">
        <h1>商品の詳細</h1>
        <form action="ShousaiToCart" method="post">
            <table>
                <tr>
                    <td>
                        <img src="honsyousai.png" alt="本の画像" class="product-image"><br>
                        <span class="syousai">商品名：${goods.goods_name}</span><br>
                        <span class="syousai">金額：￥<fmt:formatNumber value="${goods.price}" pattern="#,##0"/>
                        					<!--${goods.price}  --></span><br>
                        <span class="syousai">作者：${goods.writer}</span><br>
                        <span class="syousai">ジャンル：${goods.book_kinds}</span><br>
                        <input type="hidden" name="goods_id" value="${goods.goods_id}">
                        <br>
                        <select name="quantity">
                            <c:forEach var="a" begin="1" end="5" step="1">
                                <option value="${a}">${a}</option>
                            </c:forEach>
                        </select>
                        <br><br>
                        <input type="submit" value="決定">
                    </td>
                </tr> 
            </table>
        </form>
        <div style="margin-top: 20px;">
            <a href="/ec_takara/jsp/shohin.jsp" class="button-link">商品一覧</a>
            <a href="/ec_takara/jsp/cart.jsp" class="button-link">カートを見る</a>
        </div>
    </div>
</body>
</html>
