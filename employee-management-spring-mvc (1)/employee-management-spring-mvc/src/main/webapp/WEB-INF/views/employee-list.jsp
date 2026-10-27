<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee List</title>
    <style>
        body{font-family:Arial,sans-serif;max-width:1000px;margin:40px auto;padding:0 18px;color:#222}
        table{border-collapse:collapse;width:100%;margin-top:20px}th,td{border:1px solid #ddd;padding:10px;text-align:left}
        th{background:#f2f4f7}.success{background:#e8f5e9;padding:12px;color:#1b5e20}
        .button{display:inline-block;padding:10px 14px;background:#1459a6;color:white;text-decoration:none;border-radius:4px}
    </style>
</head>
<body>
    <h1>Employee Management System</h1>
    <c:if test="${not empty successMessage}">
        <p class="success"><c:out value="${successMessage}"/></p>
    </c:if>
    <a class="button" href="${pageContext.request.contextPath}/employees/new">Register Employee</a>
    <c:choose>
        <c:when test="${empty employees}">
            <p>No employees have been registered yet.</p>
        </c:when>
        <c:otherwise>
            <table>
                <thead><tr><th>ID</th><th>Name</th><th>Email</th><th>Department</th><th>Salary</th></tr></thead>
                <tbody>
                <c:forEach items="${employees}" var="employee">
                    <tr>
                        <td><c:out value="${employee.id}"/></td>
                        <td><c:out value="${employee.name}"/></td>
                        <td><c:out value="${employee.email}"/></td>
                        <td><c:out value="${employee.department}"/></td>
                        <td><c:out value="${employee.salary}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>
