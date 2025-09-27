<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sửa User</title>
</head>
<body>
<h2>Sửa User</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form action="update" method="post">
    <input type="hidden" name="id" value="${user.id}"/>
    
    <label>Username: </label>
    <input type="text" name="username" value="${user.username}" required/><br/><br/>
    
    <label>Password: </label>
    <input type="password" name="password" value="${user.password}" required/><br/><br/>
    
    <label>Role: </label>
    <select name="roleid">
        <option value="1" ${user.roleid == 1 ? 'selected' : ''}>Admin</option>
        <option value="2" ${user.roleid == 2 ? 'selected' : ''}>User</option>
    </select><br/><br/>
    
    <button type="submit">Cập nhật</button>
    <a href="admin-user">Quay lại</a>
</form>
</body>
</html>
