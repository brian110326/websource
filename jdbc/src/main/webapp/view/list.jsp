<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="dao.TodoDao"%>
<%@ page import="java.util.List"%>
<%@ page import="dto.TodoDto"%>
<%@ include file="../include/header.jsp" %>

<%
    // DB 연동
    TodoDao dao = new TodoDao();
    List<TodoDto> list = dao.getList();
%>

<h1>Todo List</h1>
<table class="table">
  <thead>
    <tr>
      <th scope="col">번호</th>
      <th scope="col">제목</th>
      <th scope="col">작성일자</th>
      <th scope="col">완료여부</th>
    </tr>
  </thead>
  <tbody>
    <% for(TodoDto dto : list){ %>
    <tr>
      <th scope="row"><%=dto.getNumber()%></th>
      <td><%=dto.getTitle()%></td>
      <td><%=dto.getCreated_at()%></td>
      <td>@<%=dto.isCompleted()%></td>
    </tr>
    <% } %>
  </tbody>
</table>
<%@ include file="../include/footer.jsp" %>