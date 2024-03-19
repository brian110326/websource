<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ page import="dao.TodoDao"%>
<%@ page import="dto.TodoDto"%>

<%
     request.setCharacterEncoding("utf-8");
    // 제목 클릭 시 no 가져오기
    String no = request.getParameter("no");

    // DB작업 insert
    TodoDao dao = new TodoDao();

    TodoDto todo = dao.getRow(no);

    // todo를 read.jsp에 보여주기
    request.setAttribute("todo",todo);

    pageContext.forward("read.jsp");
%>