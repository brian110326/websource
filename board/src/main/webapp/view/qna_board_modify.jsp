<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<%@include file="/include/header.jsp"%>
<!-- Main content -->
<section class="content">
	<div class="box box-primary">
		<div class="box-header">
			<h3 class="box-title">Board Modify</h3>
		</div>
		<div style="height:20px"></div>
		<form action='<c:url value="/qUpdate.do" />' method="post" role="form">
			<div class="box-body">
				<div class="form-group row">
					<label for="name" class="col-sm-2 col-form-label">글쓴이</label>
					<div class="col-sm-10" >
					<input type="text" name="name" size="10" class="form-control"	maxlength='10' value="${dto.name}">
					</div>
				</div>
				<div class="form-group row">
					<label for="title" class="col-sm-2 col-form-label">제목</label>
					<div class="col-sm-10">
						<input type="text" name="title" size="50" class="form-control"	maxlength='100' value="${dto.title}">
					</div>
				</div>
				<div class="form-group row">
					<label for="content" class="col-sm-2 col-form-label">내용</label>
					<div class="col-sm-10">
						<textarea name='content' cols='60' class="form-control" rows='15'>${dto.content}</textarea>
					</div>
				</div>
				<div class="form-group row">
					<label for="name" class="col-sm-2 col-form-label">비밀번호</label>
					<div class="col-sm-10">
						<input type="password" name="password" class="form-control" size="10" maxlength='10'>
					</div>
				</div>
				<div class="form-group row">
					<label for="filename" class="col-sm-2 col-form-label">파일첨부</label>
					<div class="col-sm-10">
						<a href='<c:url value="/view/download.jsp?fileName=${dto.attatch}" />'>${dto.attatch}</a>
					</div>
				</div>
				<div style="height:20px"></div>
				<input type="hidden" name="bno" value="${dto.bno}">
				<div class="box-footer text-center">
					<button type="submit" class="btn btn-primary">수정</button>
					<button type="button" class="btn btn-danger">목록</button>
				</div>
				<div style="height:20px"></div>
			</div>
			<%-- 아무데나 가능 form 태그 안에만 있으면 됨 --%>
			<%-- BoardModifyAction에서 bno를 가져올 것이 없었음 --%>
			
			<input type="hidden" name="page" value="${searchDto.page}">
			<input type="hidden" name="amount" value="${searchDto.amount}">
			<input type="hidden" name="criteria" value="${searchDto.criteria}">
			<input type="hidden" name="keyword" value="${searchDto.keyword}">
		</form>
	</div>
</section>
<form action='<c:url value="/qList.do" />' method="get" id="actionForm">
			<input type="hidden" name="page" value="${searchDto.page}">
			<input type="hidden" name="amount" value="${searchDto.amount}">
			<input type="hidden" name="criteria" value="${searchDto.criteria}">
			<input type="hidden" name="keyword" value="${searchDto.keyword}">
</form>
<script src='<c:url value="/js/modify.js" />'></script>
<%@include file="/include/footer.jsp"%>
