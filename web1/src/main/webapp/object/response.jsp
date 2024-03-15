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
        //          ==> 둘다 Servlet에서 주로 사용, JSP에서는 request, out을 많이 사용

    // sendRedirect(경로)
    // http://localhost:8080/response/response.jsp 요청
    // 다른 경로로 이동을 시키는 메소드
    // response.sendRedirect("/basic/input.html");
    response.sendRedirect("https://www.naver.com");
%>