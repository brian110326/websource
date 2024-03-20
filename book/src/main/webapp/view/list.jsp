<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ include file="/include/header.jsp" %>

<h3 class="border-bottom mb-3">도서목록</h3>

<table class="table table-bordered">
  <thead>
    <tr class="table-success">
      <th scope="col" class="text-center">코드</th>
      <th scope="col" class="text-center">제목</th>
      <th scope="col" class="text-center">작성자</th>
      <th scope="col" class="text-center">가격</th>
    </tr>
  </thead>

  <tbody>
    <c:forEach var="dto" items="${book}">
      <tr>
        <th scope="row">${dto.code}</th>
        <td><a href='<c:url value="/read?code=${dto.code}" />' class="text-decoration text-reset">${dto.title}</a></td>
        <td>${dto.writer}</td>
        <td>${dto.price}원</td> 
      </tr>
    </c:forEach>
  </tbody>
</table>

<%@ include file="/include/section.jsp" %>

<%@ include file="/include/footer.jsp" %>