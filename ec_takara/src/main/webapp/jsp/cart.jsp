<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>カート画面</title>
<!-- CSSファイルを読み込む -->
<link href="/ec_takara/jsp/Style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="container">
        <h2>カート画面</h2>
        <table class="cart-table">
            <tr class="table-header">
                <td>商品名</td>
                <td>個数</td>
                <td>金額</td>
                <td>操作</td>
            </tr>
            <!-- 初期値として合計を0に設定 -->
            <c:set var="total" value="0" />
            <c:forEach var="orderlistitem" items="${cartlist}">
                <tr class="table-row">
                    <td><c:out value="${orderlistitem.goods_name}"/></td>
                    <td><c:out value="${orderlistitem.quantity}"/></td>
                    <td><c:out value="${orderlistitem.price}"/>円</td>
                    <td>
                        <form action="CartDeleteController" method="post" style="display:inline;">
                            <!-- 商品IDを隠しフィールドとして送信 -->
                            <input type="hidden" name="goods_id" value="${orderlistitem.goods_id}" />
                            <input type="submit" value="削除" class="delete-button">
                        </form>
                    </td>
                    <c:set var="total" value="${total+(orderlistitem.price*orderlistitem.quantity)}"/>
                </tr>
            </c:forEach>
            <tr class="total-row">
                <td colspan="4" class="total-cell">合計金額: <strong><c:out value="${total}"/>円</strong></td>
            </tr>
        </table>
        <div class="payment-method">
            <span>お支払い方法:</span>
            <select name="payment" class="payment-select">
                <option value="代引き">代引き</option>
                <option value="コンビニ払い">コンビニ払い</option>
                <option value="pay">TakaPay</option>
                <option value="クレカ">クレカ</option>
            </select>
        </div>
        <form action="/ec_takara/ToConfirmController" method="post">
            <input type="submit" value="購入確認へ" class="submit-button">
        </form>
        <a href="/ec_takara/jsp/shohin.jsp">商品一覧に戻る</a>
    </div>
    <c:if test="${cartemptyerror}">
        <p class="error-message">カートが空ですぞ❣</p>
    </c:if>
</body>
</html>
