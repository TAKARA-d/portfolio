<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- CSSファイルを読み込む -->
<link href="/ec_takara/jsp/Style.css" rel="stylesheet" type="text/css">
<table class="included-table">
    <tr>
        <td>user_id</td><td>user_name</td><td>sex</td>
        <td>address</td><td>b_day</td><td>age</td>
        <td>generation</td><td>goods_id</td><td>goods_name</td>
        <td>writer</td><td>price</td><td>book_kinds</td><td>quantity</td>
    </tr>
    <c:forEach var="tblistitem" items="${tblist}">
        <tr>
            <td><c:out value="${tblistitem.user_id}"/></td>
            <td><c:out value="${tblistitem.user_name}"/></td>
            <td><c:out value="${tblistitem.sex}"/></td>
            <td><c:out value="${tblistitem.address}"/></td>
            <td><c:out value="${tblistitem.b_day}"/></td>
            <td><c:out value="${tblistitem.age}"/></td>
            <td><c:out value="${tblistitem.generation}"/></td>
            <td><c:out value="${tblistitem.goods_id}"/></td>
            <td><c:out value="${tblistitem.goods_name}"/></td>
            <td><c:out value="${tblistitem.writer}"/></td>
            <td><c:out value="${tblistitem.price}"/></td>
            <td><c:out value="${tblistitem.book_kinds}"/></td>
            <td><c:out value="${tblistitem.quantity}"/></td>
        </tr>
    </c:forEach>
</table>