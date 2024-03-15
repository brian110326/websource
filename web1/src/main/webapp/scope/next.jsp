<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%
    //id, name, age 가져오기 => info.jsp a태그가 보내준 것
    // get, post 방식을 사용할때
    // String id = request.getParameter("id");
    // String name = request.getParameter("name");
    // int age = Integer.parseInt(request.getParameter("age"));

    // String id = (String)session.getAttribute("id");
    // Type mismatch: cannot convert from Object to String
    // 형변환 필요
    // String name = (String)session.getAttribute("name");
    //String age = (String)session.getAttribute("age");
    // Integer age = (Integer)session.getAttribute("age");

     String id = (String)request.getAttribute("id");
    // Type mismatch: cannot convert from Object to String
    // 형변환 필요
    String name = (String)request.getAttribute("name");
    //String age = (String)session.getAttribute("age");
    Integer age = (Integer)request.getAttribute("age");
%>

<h3>next.jsp</h3>
<h3>id : <%=id%></h3>
<h3>name : <%=name%></h3>
<h3>age : <%=age%></h3>

<h3>
    <a href="next2.jsp">다음 페이지</a>
</h3>