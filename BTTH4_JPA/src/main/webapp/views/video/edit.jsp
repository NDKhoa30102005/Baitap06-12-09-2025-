<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sửa Video</title>
</head>
<body>
<h2>Sửa Video</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form action="update" method="post">
    <input type="hidden" name="id" value="${video.id}"/>
    
    <label>Tiêu đề: </label>
    <input type="text" name="title" value="${video.title}" required/><br/><br/>
    
    <label>Mô tả: </label>
    <textarea name="description" rows="4" cols="50">${video.description}</textarea><br/><br/>
    
    <label>URL: </label>
    <input type="text" name="url" value="${video.url}" required/><br/><br/>
    
    <button type="submit">Cập nhật</button>
    <a href="admin-video">Quay lại</a>
</form>
</body>
</html>
