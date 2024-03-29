<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ include file="/include/header.jsp" %>

<h3 class="border-bottom mb-3">도서 수정</h3>

<form action='<c:url value="/modify.do" />' method="post">
    <div class="row mb-3">
        <div class="col">
            <input type="text" class="form-control" placeholder="code" name="code" id="code">
        </div>

        <div class="col">
            <input type="text" class="form-control" placeholder="price" name="price" id="price">
        </div>
    </div>
    <div>
        <button type="submit" class="btn btn-success">수정</button>
        <a href='<c:url value="/list.do" />' class="btn btn-primary">목록</a>
    </div>
</form>

<%@ include file="/include/section.jsp" %>

<script src='<c:url value="/js/modify.js" />'></script>

<%@ include file="/include/footer.jsp" %>