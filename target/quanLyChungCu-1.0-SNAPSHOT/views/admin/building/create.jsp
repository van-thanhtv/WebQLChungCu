
<%--
  Created by IntelliJ IDEA.
  User: thongpro
  Date: 3/31/22
  Time: 9:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="d-sm-flex align-items-center justify-content-between mb-4 offset-5">
    <h1 class="h3 mb-0 text-gray-800">Building Management</h1>
</div>
<form class=" row mt-3 ms-0 pe-0" action="storeBuilding" method="post">
    <div class="mb-3 col-12">
        <label class="form-label fw-bold">NameBuilding</label>
        <input type="text" class="form-control" name="nameBuilding">
    </div>
    <div class="mb-3 col-12">
        <label class="form-label fw-bold">Address</label>
        <input type="text" class="form-control" name="address">
    </div>
<%--    <div class="mb-3 col-12">--%>
<%--        <label class="form-label fw-bold">Number of Floors</label>--%>
<%--        <input type="text" class="form-control" name="quantity">--%>
<%--    </div>--%>
    <input type="hidden" value="${sessionScope.user.id}">
    <div class="mt-3">
        <button  class="btn btn-success">Thêm</button>
        <button type="reset" class="btn btn-primary">Làm mới</button>
    </div>
</form>
<br>
