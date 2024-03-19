<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%
    Cookie c1 = new Cookie("name","AAA");
    Cookie c2 = new Cookie("gender","Male");
    Cookie c3 = new Cookie("age",33);
    response.addCookie(c1);
    response.addCookie(c2);
    response.addCookie(c3);

%>

<h3>쿠키 데이터 저장</h3>
<a href="cookieTest.jsp">쿠키 확인</a>