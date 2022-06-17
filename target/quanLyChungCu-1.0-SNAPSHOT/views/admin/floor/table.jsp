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
<c:if test="${empty list}">
    <p class="alert alert-warning">
        Vui Lòng Thêm Mới Dữ Liệu
    </p>
</c:if>
<c:if test="${!empty sessionScope.error}">
    <div class="alert alert-danger">
            ${sessionScope.error}
    </div>
    <c:remove var="error" scope="session"/>
</c:if>
<c:if test="${!empty sessionScope.message}">
    <div class="alert alert-success">
            ${sessionScope.message}
    </div>
    <c:remove var="message" scope="session"/>
</c:if>
<table class="table table-success table-striped">
    <thead>
    <tr>
        <th scope="col">NameBuilding</th>
        <th scope="col">NameFloor</th>
        <th scope="col">Creator</th>
        <th scope="col">DateCreate</th>
        <th colspan="2">Manipulation</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${list}" var="floor">
        <tr>
            <td>${floor.idBuilding.nameBuilding}</td>
            <td>${floor.nameFloor}</td>
            <td>${floor.idBuilding.user.name}</td>
            <td><fmt:formatDate value="${floor.dateCreate}" pattern="dd/MM/yyyy"/></td>
            <td>
                <form action="/editFloor" method="post">
                    <input type="hidden" value="${floor.id}" name="id">
                    <button class="btn btn-primary">Update</button>
                </form>
            </td>
            <td>
                <button class="btn btn-danger" data-toggle="modal" data-target="#c${floor.id}">Delete</button>
            </td>
            <div id="c${floor.id}" class="modal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title">Xác nhận</h3>
                            <button type="button" class="btn-close" data-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <h5>Bạn muốn xóa  ${floor.nameFloor} ?</h5>
                        </div>
                        <div class="modal-footer">
                            <form action="/deleteFloor" method="post">
                                <input type="hidden" value="${floor.id}" name="id">
                                <button class="btn btn-danger">Delete</button>
                            </form>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal"
                                    aria-label="Close">Hủy
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </tr>
    </c:forEach>
    </tbody>
</table>


