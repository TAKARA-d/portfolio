<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ジャンル別売上だよ❣</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <h2>売れている書籍の種類</h2>

    <!-- 売上データを表で表示 -->
    <table border="1">
        <thead>
            <tr>
                <th>書籍の種類</th>
                <th>売上数</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="entry" items="${sessionScope.salesData}">
                <tr>
                    <td>${entry.key}</td>
                    <td>${entry.value}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <canvas id="bookSalesChart" width="400" height="200"></canvas>

    <script>
        // JSTLを使ってセッションからデータを取得し、JavaScriptのオブジェクトに変換
        const salesData = {
            <c:forEach var="entry" items="${sessionScope.salesData}">
                '${entry.key}': ${entry.value}<c:if test="${!entry.last}">,</c:if>
            </c:forEach>
        };

        // book_kindsと売上数を配列に変換
        const labels = Object.keys(salesData);
        const data = Object.values(salesData);

        const ctx = document.getElementById('bookSalesChart').getContext('2d');
        const chart = new Chart(ctx, {
            type: 'bar', // グラフのタイプ
            data: {
                labels: labels, // X軸のラベル
                datasets: [{
                    label: '売上数',
                    data: data, // 売上数のデータ
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    </script>
</body>
</html>
