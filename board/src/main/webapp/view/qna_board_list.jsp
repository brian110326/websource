<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/include/header.jsp"%>
<!-- Main content -->
<section class="content">
	<div class="box box-primary">
		<div class="box-header">
			<h3 class="box-title">List Board</h3>
		</div>
		<div class="row justify-content-between">
			<div class="col-md-4">
				<a href='<c:url value="/view/qna_board_write.jsp" />' class="btn btn-success">글쓰기</a>
			</div><!--글쓰기 버튼-->
			<div class="col-md-5"><!--검색 들어갈 부분-->
				<form action="" method="get">
					<div class="form-group">
						<select name="criteria" id="criteria" class="form-control">
							<option value="n">---------------</option>
							<option value="title">제목</option>
							<option value="content">내용</option>
							<option value="name">작성자</option>
						</select>
					</div>
					<div class="form-group">
						<input type="text" name="keword" id="" class="form-control">
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-primary">검색</button>
					</div>				
				</form>
			</div>
		</div>
		<br>
		<table class="table table-bordered">
			<tr>
				<th class='text-center' style='width:100px'>번호</th>
				<th class='text-center'>제목</th>
				<th class='text-center'>작성자</th>
				<th class='text-center'>날짜</th>
				<th class='text-center' style='width:100px'>조회수</th>
			</tr>
			<c:forEach var="dto" items="${list}">
				<tr><!-- 리스트 목록 보여주기 -->
					<td class='text-center'>${dto.bno}</td><!--번호-->
					<td><a href='<c:url value="qRead.do?bno=${dto.bno}" />'>${dto.title}</a></td><!--제목-->
					<td class='text-center'>${dto.name}</td><!--작성자-->
					<td class='text-center'>${dto.regDate}</td><!--날짜-->
					<td class='text-center'><span class="badge badge-pill badge-primary">${dto.readCount}</span></td>
				</tr>	
			</c:forEach>
		</table>
		<div class="container">
			<div class="row  justify-content-md-center">
				<nav aria-label="Page navigation example">
				  <ul class="pagination"><!--하단의 페이지 나누기 부분-->

				  </ul>
				</nav>					
			</div>
		</div>
		<div style="height:20px"></div>
	</div>	
</section>
<%@include file="/include/footer.jsp"%>
