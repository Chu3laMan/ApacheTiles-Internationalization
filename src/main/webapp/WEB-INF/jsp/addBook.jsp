<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
<a href="?language=en" >English</a>|<a href="?language=fr"
>French</a><br>
<spring:message code="addBook.title" /><br>
 <!-- <tiles:insertAttribute name="header" /> -->
<hr />
<form:form method="POST" modelAttribute="newBook" >

<spring:message code="addBook.form.label.bookName" />
<form:input type="text" path="bookName"/><br>

 
<spring:message code="addBook.form.label.author" />
<form:input type="text" path="author"/><br>

 
<spring:message code="addBook.form.label.isbn" />
<form:input path="isbn"/><br/>
<input type="submit" value="submit"/>
</form:form>
<tiles:insertAttribute name="footer" />
</body>
</html>