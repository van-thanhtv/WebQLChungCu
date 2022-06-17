<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<h2>
    <fmt></fmt>
</h2>
<c:if test="${empty ds}">
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
        <th scope="col">Avatar</th>
        <th scope="col">Name</th>
        <th scope="col">Address</th>
        <th scope="col">Phone</th>
        <th scope="col">Email</th>
        <th scope="col">Birthday</th>
        <th scope="col">Gender</th>
        <th scope="col">Permission</th>
        <th colspan="2">Manipulation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ds}" var="user">
        <tr>
            <td><img height="50px" src="/images/avatar/${user.avatar}"></td>
            <td>${user.name}</td>
            <td>${user.address}</td>
            <td>${user.numberPhone}</td>
            <td>${user.email}</td>
            <td><fmt:formatDate value="${user.birthday}" pattern="dd/MM/yyyy"/></td>
            <td>
                <c:choose>
                    <c:when test="${user.sex==1}">Boy</c:when>
                    <c:when test="${user.sex==0}">Girl</c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
            </td>
            <td>${user.isAdmin==1 ? "Admin" : "User"}</td>
            <td>
                <form action="edit" method="post">
                    <input type="hidden" value="${user.id}" name="id">
                    <button class="btn btn-primary">Update</button>
                </form>
            </td>
            <td>
                <button class="btn btn-danger" data-toggle="modal" data-target="#a${user.id}">Delete</button>
            </td>
            <div id="a${user.id}" class="modal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title">Xác nhận</h3>
                            <button type="button" class="btn-close" data-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <h5>Bạn muốn xóa người dùng ${user.name} ?</h5>
                        </div>
                        <div class="modal-footer">
                            <form action="delete" method="post">
                                <input type="hidden" value="${user.id}" name="id">
                                <button class="btn btn-danger">Xóa</button>
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
