<%--
  Created by IntelliJ IDEA.
  User: thongpro
  Date: 3/31/22
  Time: 5:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<table class="table table-success table-striped">
    <thead>
    <tr>
        <th scope="col">ID Contract</th>
        <th scope="col">Name Contract</th>
        <th scope="col">CMND</th>
        <th scope="col">Name Customer</th>
        <th scope="col">Email</th>
        <th scope="col">Phone</th>
        <th scope="col">Building</th>
        <th scope="col">Address Building</th>
        <th scope="col">Floor</th>
        <th scope="col">Room</th>
        <th scope="col">Acreage</th>
        <th><a class="btn btn-primary" href="/Contract">Back</a></th>

    </tr>
    </thead>
    <tbody>
        <tr>
            <td>HD${contract.id}</td>
            <td>${contract.name}</td>
            <td>${contract.idCustomer.cmnd}</td>
            <td>${contract.idCustomer.nameCustomer}</td>
            <td>${contract.idCustomer.email}</td>
            <td>${contract.idCustomer.phone}</td>
            <td>${contract.idRoom.idFloor.idBuilding.nameBuilding}</td>
            <td>${contract.idRoom.idFloor.idBuilding.address}</td>
            <td>${contract.idRoom.idFloor.nameFloor}</td>
            <td>${contract.idRoom.nameRoom}</td>
            <td><fmt:formatNumber value="${contract.idRoom.acreage}" pattern="#,###"/> m <sup>2</sup></td>
        </tr>
    </tbody>
</table>


