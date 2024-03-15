<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%
    pageContext.forward("forward.jsp");
    // 주소는 info2.jsp이지만 화면에 보이는 것은 forward.jsp
    // info2.jsp에 부여된 request를 forward.jsp에 넘겨주는것
    // info2.jsp에서 할 수 있는 작업들을 forward.jsp에서 할 수 있게 됨

    // Servlet으로 주로 사용하게됨
%>