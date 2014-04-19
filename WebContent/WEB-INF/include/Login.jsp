<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login to My Site</title>
</head>
<style type="text/css">
	p {margin:0 0 2em}
</style>
<body>

<h1 style="margin-bottom:0">Welcome to My Site</h1>
<p>You must login first. Please enter your information below:</p>

<c:if test="${validationMessage != null}">
	<p style="color:#CC0000">${validationMessage}</p>
</c:if>


<form action="DBProjectServlet" method="post">
	Name: <input type="text" name="name" value="${param.name}"><br />
	Initial Language: <select name="language">
						<option value="en" <c:if test="${'en' == param.language}">selected="selected"</c:if>>English</option>
						<option value="es" <c:if test="${'es' == param.language}">selected="selected"</c:if>>Español (Spanish)</option>
						<option value="zh" <c:if test="${'zh' == param.language}">selected="selected"</c:if>>中國的 (Chinese)</option>
						<option value="tlh" <c:if test="${'tlh' == param.language}">selected="selected"</c:if>>tlhIngan (Klingon)</option>
					  </select><br />
	User Name: <input type="text" name="userName" value="${param.userName}"><br />
	Password: <input type="password" name="password" value=""><br />
	<input type="submit" name="logIn" value="Log In Now" />
</form>


</body>
</html>