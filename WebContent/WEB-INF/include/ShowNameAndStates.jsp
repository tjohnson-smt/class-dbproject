<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<sql:query var="countries">
	SELECT * from countries;
</sql:query>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${sessionScope.language}">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DB Project</title>
<style type="text/css">
	th {text-align:left}
	td {padding-right:3em}
</style>
</head>
<body>
<fmt:message key='Hello'/> ${sessionScope.name}<br />
<br />

<form action="DBProjectServlet" method="post" enctype="multipart/form-data">
	<fmt:message key='ChangeLanguage'/>
	<select name="language" onChange="window.location='?language='+this.value">
		<option value="en" <c:if test="${'en' == sessionScope.language}">selected="selected"</c:if>>English</option>
		<option value="es" <c:if test="${'es' == sessionScope.language}">selected="selected"</c:if>>Español (Spanish)</option>
		<option value="zh" <c:if test="${'zh' == sessionScope.language}">selected="selected"</c:if>>中國的 (Chinese)</option>
		<option value="tlh" <c:if test="${'tlh' == sessionScope.language}">selected="selected"</c:if>>tlhIngan (Klingon)</option>
	</select><br />
	 <input type="file" name="fileUpload" /><br />
	 <input type="submit" name="upload" value="Upload"><br />
	<input type="submit" name="logOut" value="<fmt:message key='LogOutNow'/>" />
</form><br />
<br />

<fmt:message key='SelectCountry'/>
<form action="#" method="post">
	<select name="country_id" onChange="window.location='?country_id='+this.value">
		<c:forEach items="${countries.rows}" var="country">
			<option value="${country.country_id}" <c:if test="${country.country_id == param.country_id}">selected="selected"</c:if>>${country.name}</option>
		</c:forEach>	
	</select>
</form><br />
<br />

<fmt:message key='HeresListStates'/><br />
<table>
	<tr>
		<th><fmt:message key='Code'/></th>
		<th><fmt:message key='Name'/></th>
	</tr>
    <c:forEach items="${states}" var="state">
        <tr>
            <td>${state.stateCode}</td>
            <td>${state.stateName}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>