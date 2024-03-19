<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ page import="dao.TodoDao"%>
<%@ page import="dto.TodoDto"%>
<%
    request.setCharacterEncoding("utf-8");
    // 사용자가 입력한 todo 데이터를 가져오기
    String title = request.getParameter("title");
    String description = request.getParameter("description");

    // DB작업 insert
    TodoDao dao = new TodoDao();
    TodoDto insertdto = new TodoDto();

    insertdto.setTitle(title);
    insertdto.setDescription(description);

    int result = dao.insert(insertdto);

    // 화면이동(list) : 위의 결과(데이터)를 같이 가져가는지 여부 확인
    // 같은 view 폴더에 있으니 list.jsp만 작성
    // 다른 폴더에 존재한다면 => /폴더명/파일명.jsp
    
    response.sendRedirect("list.jsp");
%>