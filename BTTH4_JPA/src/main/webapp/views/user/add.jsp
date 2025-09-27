<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Thêm User</title>
</head>
<body>
<h2>Thêm User Mới</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form action="create" method="post">
    <label>Username: </label>
    <input type="text" name="username" required/><br/><br/>
    
    <label>Password: </label>
    <input type="password" name="password" required/><br/><br/>
    
    <label>Role: </label>
    <select name="roleid">
        <option value="1">Admin</option>
        <option value="2" selected>User</option>
    </select><br/><br/>
    
    <button type="submit">Thêm</button>
    <a href="admin-user">Quay lại</a>
</form>
</body>
</html>
