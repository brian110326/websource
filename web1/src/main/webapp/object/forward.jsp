<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%
        // JSP 내장객체
        // 1) HttpServletRequest request : 변수명 고정
        // 2) HttpServletResponse response
        // 3) JspWriter객체 out
        // 4) PageContext pageContext : JSP 페이지에 대한 정보를 저장하고 있는 객체
        //      (1) forward()
        //      (2) include("포함할 페이지 경로") : 디자인 템플릿 구성 시 사용 

    // http://localhost:8080/object/forward.jsp ==> content.jsp화면으로 넘어가지만 주소는 바뀌지않음
    // 주소는 요청 주소인 상태
    // sendRedirect / forward 구별하는것이 중요★
    
    pageContext.forward("content.jsp");
%>