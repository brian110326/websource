<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ include file="/include/header.jsp" %>


<h3 class="border-bottom mb-3">도서상세보기</h3>
<form action="" method="post">
    <div class="mb-3">
    <label for="code" class="form-label">Code</label>
    <input type="text" class="form-control" id="code" readonly value="${dto.code}" name="code">
    </div>

    <div class="mb-3">
    <label for="title" class="form-label">Title</label>
    <input type="text" class="form-control" id="title" readonly value="${dto.title}" name="title">
    </div>

    <div class="mb-3">
    <label for="writer" class="form-label">Writer</label>
    <input type="text" class="form-control" id="writer" readonly value="${dto.writer}" name="writer">
    </div>

    <div class="mb-3">
    <label for="price" class="form-label">Price</label>
    <input type="text" class="form-control" id="price" readonly value="${dto.price}" name="price">
    </div>

    <div class="mb-3">
    <label for="description" class="form-label">Description</label>
    <textarea class="form-control" id="description" rows="3" name="description" >${dto.description}</textarea>
    </div>

    <div class="mb-3">
        <a href='<c:url value="/list.do" />' class="btn btn-primary">목록</a>
    </div>

</form>

<%@ include file="/include/section.jsp" %>

<%@ include file="/include/footer.jsp" %>