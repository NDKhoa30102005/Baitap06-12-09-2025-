<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quản lý Video</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        th { background-color: #eee; }
        a { margin: 0 5px; }
    </style>
</head>
<body>
<h2>Danh sách Video</h2>

<form method="get">
    <input type="text" name="keyword" placeholder="Tìm kiếm tiêu đề..." value="${param.keyword}"/>
    <button type="submit">Search</button>
    <a href="admin-video/create">Thêm mới</a>
</form>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<table>
    <tr>
        <th>ID</th>
        <th>Tiêu đề</th>
        <th>Mô tả</th>
        <th>URL</th>
        <th>Người tạo</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="video" items="${videos}">
        <tr>
            <td>${video.id}</td>
            <td>${video.title}</td>
            <td>${video.description}</td>
            <td><a href="${video.url}" target="_blank">Link</a></td>
            <td>${video.user.username}</td>
            <td>
                <a href="admin-video/edit?id=${video.id}">Edit</a>
                <a href="admin-video/delete?id=${video.id}" onclick="return confirm('Bạn có chắc muốn xóa?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
