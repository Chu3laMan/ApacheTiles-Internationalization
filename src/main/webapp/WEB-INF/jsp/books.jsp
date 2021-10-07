<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
<a href="?language=en" >English</a>|<a href="?language=fr"
>French</a><br>

    <table>
            <thead>
                <tr>
                    <th><spring:message code="addBook.form.label.bookName" /></th>
                    <th><spring:message code="addBook.form.label.author" /></th>
                    <th><spring:message code="addBook.form.label.isbn" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${books}" var="book">
                    <tr>
                        <td>${book.bookName}</td>
                        <td>${book.author}</td>
                        <td>${book.isbn}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
      <tiles:insertAttribute name="footer" />
  </body>
</html>