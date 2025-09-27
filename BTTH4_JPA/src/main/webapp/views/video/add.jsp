<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Thêm Video</title>
</head>
<body>
<h2>Thêm Video Mới</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form action="create" method="post">
    <label>Tiêu đề: </label>
    <input type="text" name="title" required/><br/><br/>
    
    <label>Mô tả: </label>
    <textarea name="description" rows="4" cols="50"></textarea><br/><br/>
    
    <label>URL: </label>
    <input type="text" name="url" required/><br/><br/>
    
    <button type="submit">Thêm</button>
    <a href="admin-video">Quay lại</a>
</form>
</body>
</html>
