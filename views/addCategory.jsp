<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Thêm mới danh mục</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
      background-color: #f4f4f4;
    }
    h1 {
      color: #333;
    }
    form {
      background: #fff;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
      max-width: 500px;
      margin: auto;
    }
    label {
      display: block;
      margin: 10px 0 5px;
    }
    input[type="text"] {
      width: calc(100% - 20px);
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }
    input[type="checkbox"] {
      width: auto;
      margin-top: 10px;
    }
    button {
      background-color: #007BFF;
      color: white;
      padding: 10px 15px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 16px;
    }
    button:hover {
      background-color: #0056b3;
    }
    .message {
      margin-top: 15px;
      font-weight: bold;
      color: red; /* Màu cho thông báo lỗi */
    }
  </style>
</head>
<body>
<h1>Thêm mới danh mục</h1>
<form action="${pageContext.request.contextPath}/categories/add" method="post">
  <c:if test="${not empty error}">
    <p style="color: red">${error}</p>
  </c:if>
  <label for="cateName">Tên danh mục:</label>
  <input type="text" id="cateName" name="cateName" value="${category.cateName}" />

  <label for="description">Mô tả:</label>
  <input type="text" id="description" name="description" value="${category.description}"/>

  <div style="display: flex; justify-content: center; align-items: center; gap: 20px">
    <label>Trạng thái:</label>
    <input type="radio" id="status" name="status" value="true"  <c:if test="${category.status}">checked</c:if> />
    <label for="status">Có</label>
    <input type="radio" id="notStatus" name="status" value="false"  <c:if test="${not category.status}">checked</c:if> />
    <label for="notStatus">Không</label>
  </div>

  <button type="submit">Thêm</button>
  <a href="/categories">Quay lại</a>
</form>



</body>
</html>