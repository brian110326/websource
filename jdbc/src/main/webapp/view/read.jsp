<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ include file="../include/header.jsp" %>
<%@ page import="dto.TodoDto"%>
<%
   // TodoDto todo = (TodoDto)request.getAttribute("todo");
%>
<h1 class="mt-5">Todo View</h1>
<form action="" method="post">
    <div class="mb-3">
        <label for="title" class="form-label">title</label>
        <%-- <input type="text" class="form-control" id="title" placeholder="title" name="title" value="<%=todo.getTitle()%>"> --%>
        <input type="text" class="form-control" id="title" placeholder="title" name="title" value="${todo.title}" readonly>
    </div>
    <div class="mb-3">
        <label for="created_at" class="form-label">createdAt</label>
        <%-- <input type="text" class="form-control" id="createdAt" placeholder="createdAt" name="createdAt" value="<%=todo.getCreatedAt()%>"> --%>
        <input type="text" class="form-control" id="created_at" placeholder="created_at" name="created_at" value="${todo.created_at}" readonly>
    </div>
    <div class="mb-3">
        <label for="completed" class="form-check-label">completed</label>
        <%-- completed 가 true 면 check 표시 --%>
        <input type="checkbox" name="completed" id="completed" class="form-check-input" name="completed" disabled <c:out value="${todo.completed?'checked':''}" />>       
    </div>
    <div class="mb-3">
        <label for="description" class="form-label">description</label>
        <%-- <textarea class="form-control" id="description" rows="3" name="description"><%=todo.getDescription()%></textarea> --%>
        <textarea class="form-control" id="description" rows="3" name="description" readonly>${todo.description}</textarea>
    </div>
    <div>
        <a class="btn btn-primary" href='<c:url value="/modify?no=${todo.number}" />'>수정</a>
        <a class="btn btn-success" href='<c:url value="/list" />'>목록</a>
    </div>
</form>
<%@ include file="../include/footer.jsp" %>