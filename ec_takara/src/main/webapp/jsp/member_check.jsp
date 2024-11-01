<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Context-type" content="text/html; charset=UTF-8">
<title>会員登録確認画面</title>
<!-- CSSファイルを読み込む -->
<link href="/ec_takara/jsp/Style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="container">
        <h1>会員登録確認画面</h1>
        <p>以下の内容でよろしいでしょうか？</p>
        <form action="ToListController" method="POST">
            <table class="confirmation-table">
                <tr>
                    <td class="label">ログインID</td>
                    <td><c:out value="${user.user_id}"/></td>
                </tr>
                <tr>
                    <td class="label">パスワード</td>
                    <td><c:out value="${user.password}"/></td>
                </tr>
                <tr>
                    <td class="label">名前</td>
                    <td><c:out value="${user.user_name}"/></td>
                </tr>
                <tr>
                    <td class="label">性別</td>
                    <td><c:out value="${user.sex}"/></td>
                </tr>
                <tr>
                    <td class="label">住所</td>
                    <td><c:out value="${user.address}"/></td>
                </tr>
                <tr>
                    <td class="label">生年月日</td>
                    <td><c:out value="${user.b_day}"/></td>
                </tr>
            </table>
            <input type="hidden" name="pos" value="1"/>
            <div class="button-group">
                <input type="submit" value="決定" class="submit-button">
                <a href="/ec_takara/jsp/member.jsp" class="nav-link">戻る</a>
            </div>
        </form>
    </div>
</body>
</html>
