<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:insertAttribute name="title" /></title>

<!-- Css Styles -->
    <link rel="stylesheet" href="../resources/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../resources/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="../resources/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="../resources/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="../resources/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="../resources/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="../resources/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="../resources/css/style.css" type="text/css">


<title>QR CODE  VERIFICATION</title>
</head>
<style>
img {
  display: block;
  margin-left: auto;
  margin-right: auto;
}
.inputs {
   display: block;
  margin-left: auto;
  margin-right: auto;
  text-align: center;
}
</style>

<body>
<tiles:insertAttribute name="header" />
<form method="post" action="/customer-panel/qr-verification">

<img id="qr" src="<c:url value="/customer-panel${qrCodeContent}"> </c:url>" alt="image" style = "width:20%;"/>
<div class="inputs">
<p align="center" style="color:red;">${errorMsg}</p>
<input type="text" placeholder="Enter your 6-digits" name="qrDigit">
<input type="hidden" value="${emailId}" name="email"/>
<button type="submit" class="site-btn">Continue</button>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</div>
</form>
</body>
<tiles:insertAttribute name="footer" /> 
</html>
