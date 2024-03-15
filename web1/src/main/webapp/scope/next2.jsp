<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%
    String id = (String)session.getAttribute("id");
    String name = (String)session.getAttribute("name");
    Integer age = (Integer)session.getAttribute("age");

    // session 으로 받았기 때문에 브라우저를 닫기전까지 데이터가 계속 유지가 됨
%>

<h2>next2.jsp</h2>
<h3>id : <%=id%></h3>
<h3>name : <%=name%></h3>
<h3>age : <%=age%></h3>

