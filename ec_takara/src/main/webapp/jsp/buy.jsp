<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>購入確認画面</title>
    <!-- CSSファイルを読み込む -->
    <link href="/ec_takara/jsp/Style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="container">
        <h2>注文内容の確認</h2>
        <form action="ToBuyController" method="post">
            <table class="confirmation-table">
                <tr>
                    <td class="label">お届け日時</td>
                    <td>日付指定なし</td>
                </tr>
                <tr>
                    <td class="label">お届け先</td>
                    <td>会員登録住所と同じ</td>
                </tr>
                <!-- 商品の情報を表示する部分 -->
                <c:set var="total" value="0" />
                <c:forEach var="orderlistitem" items="${cartlist}">
                    <c:set var="total" value="${total + (orderlistitem.price * orderlistitem.quantity)}" />
                </c:forEach>
                <tr>
                    <td class="label">小計</td>
                    <td><c:out value="${total}" />円</td>
                </tr>
                <tr class="total-row">
                    <td><h4>支払い合計</h4></td>
                    <td><h4><c:out value="${total}" />円</h4></td>
                </tr>
            </table>
            <div class="button-group">
                <input type="submit" value="購入" class="submit-button">
            </div>
        </form>
        <a href="/ec_takara/jsp/cart.jsp">戻る</a>
    </div>  
</body>
</html>

