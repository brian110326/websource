<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ page import="dto.TodoDto"%>
<%@ include file="/include/header.jsp" %>

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
  <%-- items="${list} : setAttribute에 쓴 이름, var=dto는 변수명 --%>
    <c:forEach var="dto" items="${list}">
      <tr>
        <th scope="row">${dto.number}</th>
        <%-- get방식이기 때문에 --%>
        <td><a href='<c:url value="/read.do?no=${dto.number}" />' class="text-decoration-none text-reset">${dto.title}</a></td>
        <td>${dto.created_at}</td>
        
        <td>
            <input type="checkbox" name="completed" id="completed" class="form-check-input" name="completed" value="true" <c:out value="${dto.completed?'checked':''}" /> >
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<%-- 현재 프로젝트는 프로젝트명을 따로 쓰지 않으므로 ..을 붙이지 않음 --%>
<%-- 만약에 localhost:8080/jdbc 프로젝트명까지 입력을 해야한다면 ..을 붙여야함 --%>
<%@ include file="/include/footer.jsp" %>