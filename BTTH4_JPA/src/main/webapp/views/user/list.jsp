<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quản lý User</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        th { background-color: #eee; }
        a { margin: 0 5px; }
    </style>
</head>
<body>
<h2>Danh sách User</h2>

<form method="get">
    <input type="text" name="keyword" placeholder="Tìm kiếm username..." value="${param.keyword}"/>
    <button type="submit">Search</button>
    <a href="admin-user/create">Thêm mới</a>
</form>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<table>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Role</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>
                <c:choose>
                    <c:when test="${user.roleid == 1}">Admin</c:when>
                    <c:otherwise>User</c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="admin-user/edit?id=${user.id}">Edit</a>
                <a href="admin-user/delete?id=${user.id}" onclick="return confirm('Bạn có chắc muốn xóa?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
