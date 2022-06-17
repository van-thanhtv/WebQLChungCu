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
    <h1 class="h3 mb-0 text-gray-800">Customer Management</h1>
</div>
<form class=" row mt-3 ms-0 pe-0" action="storeCustomer" method="post" >
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Name</label>
        <input type="text" class="form-control" name="nameCustomer">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Phone</label>
        <input type="text" class="form-control" name="phone">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Email</label>
        <input type="email" class="form-control" name="email">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">CMND</label>
        <input type="text" class="form-control" name="cmnd">
    </div>

    <div class=" p-3 mt-4 col-6">
        <label class="form-label fw-bold pe-4 me-3">Gender</label>
        <input class="form-check-input  " type="radio" value="1" checked name="sex">
        <label class="form-check-label me-5">Boy</label>
        <input class="form-check-input" type="radio" value="0" name="sex">
        <label class="form-check-label me-3">Girl</label>
    </div>
    <div class="mt-3">
        <button  class="btn btn-success">ADD</button>
        <button type="reset" class="btn btn-primary">Reset</button>
    </div>
</form>
<br>


