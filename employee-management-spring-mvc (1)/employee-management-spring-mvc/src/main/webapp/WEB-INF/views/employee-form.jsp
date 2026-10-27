<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register Employee</title>
    <style>
        body{font-family:Arial,sans-serif;max-width:760px;margin:40px auto;padding:0 18px;color:#222}
        label{display:block;margin-top:14px;font-weight:bold}
        input{box-sizing:border-box;width:100%;padding:10px;margin-top:5px}
        .error{color:#b00020;font-size:.9rem}.alert{background:#fff0f0;padding:10px;color:#900}
        button{margin-top:20px;padding:10px 18px;cursor:pointer}.nav{margin-bottom:20px}
    </style>
</head>
<body>
    <div class="nav"><a href="${pageContext.request.contextPath}/employees">Employee List</a></div>
    <h1>Employee Registration</h1>
    <p class="alert">${formError}</p>
    <form:form method="post" modelAttribute="employee"
               action="${pageContext.request.contextPath}/employees">
        <label for="name">Full name</label>
        <form:input path="name" id="name" maxlength="100"/>
        <form:errors path="name" cssClass="error"/>

        <label for="email">Email address</label>
        <form:input path="email" id="email" type="email" maxlength="150"/>
        <form:errors path="email" cssClass="error"/>

        <label for="department">Department</label>
        <form:input path="department" id="department" maxlength="100"/>
        <form:errors path="department" cssClass="error"/>

        <label for="salary">Salary</label>
        <form:input path="salary" id="salary" type="number" step="0.01" min="0.01"/>
        <form:errors path="salary" cssClass="error"/>

        <button type="submit">Save Employee</button>
    </form:form>
</body>
</html>
