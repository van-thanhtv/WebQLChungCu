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
    <h1 class="h3 mb-0 text-gray-800">Contract Management</h1>
</div>
<form class=" row mt-3 ms-0 pe-0" action="updateContract?id=${contract.id}" method="post" >
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">NameContract</label>
        <input type="text" class="form-control" name="name" value="${contract.name}">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">ID Customer</label>
        <input type="text" class="form-control" name="customer_id" value="${contract.idCustomer.id}">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">ID Room</label>
        <input type="text" class="form-control" name="room_id" value="${contract.idRoom.id}">
    </div>

    <div class="mb-3 col-6">
        <label class="form-label fw-bold">TypeContract</label>
        <select class="form-select" name="type_id" >
            <c:forEach items="${ dstype }" var="type">
                <option  ${contract.idtype.id==type.id ? "selected" : ""}  value="${ type.id }">
                        ${ type.name}
                </option>
            </c:forEach>
        </select>
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Date End</label>
        <input type="date" class="form-control" name="dateEnd" value="${contract.dateEnd}">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Price</label>
        <input type="text" class="form-control" name="price" value="${contract.price}">
    </div>

    <div class="mt-3">
        <button  class="btn btn-success">Update</button>
        <button type="reset" class="btn btn-primary">Reset</button>
    </div>
</form>
<br>


