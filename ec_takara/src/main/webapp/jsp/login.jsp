<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ログイン画面</title>
	<!-- CSSファイルを読み込む -->
    <link href="/ec_takara/jsp/Style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <h1>ログイン</h1>
    <br/>
    <%-- アカウント認証コントローラ（LoginController）へのリクエスト --%>
    <form action="/ec_takara/LoginController" method="POST">
        <center>
            <table>
                <tr>
                    <td>ログインID</td>
                    <td><input type="text" size="50" name="loginid"></td>
                </tr>
                <tr>
                    <td>パスワード</td>
                    <td><input type="password" size="51" name="password"></td>
                </tr>
            </table>
            <input type="hidden" name="pos" value="1"/>
            <c:if test="${!sessionScope.login}">
                <input type="submit" value="ログイン"/>
            </c:if>
        </center>
    </form>
    <br/>
    <%-- <form action="/ec_takara/LoginController" method="GET">
        <input type="submit" />
    </form>--%>
    <c:if test="${sessionScope.login}">
        <center>
            <jsp:include page="fail.jsp"/>
        </center>
    </c:if>
    <br/>
    <center>
        <a href="/ec_takara/jsp/member.jsp">新規会員登録はこちら</a>
    </center>
</body>
</html>
