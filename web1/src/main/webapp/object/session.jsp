<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <%
        // JSP 내장객체
        // 1) HttpServletRequest request : 변수명 고정
        // 2) HttpServletResponse response
        // 3) JspWriter객체 out
        // 4) PageContext pageContext : JSP 페이지에 대한 정보를 저장하고 있는 객체
        //      (1) forward()
        //      (2) include("포함할 페이지 경로") : 디자인 템플릿 구성 시 사용

        // 5) HttpSession session
        // 세션 : 세션을 가지고있다 ==> 특정 서버와 연결된 상태이다
        // https or http 프로토콜 특징 => 무상태
        // -무상태(stateless) <==> 상태(stateful) 
        // 무상태 : 클라이언트 상태를 저장하지 않음 : 클라이언트가 요청을하면 서버가 응답을 해주는데 무상태는 기억을 하지않음
        // 장바구니와 로그인 같은 경우 기억을 하지 않으면 계속 담아야함
        // 상태 저장 필요하다면?
        // 1) 서버 측에 저장 - 세션
        // 2) 클라이언트 측에 저장 - 쿠키 / 브라우저 저장
        // 8080 사이트 구글과 엣지에 입력해보기 ==> 쿠키가 다름 , 브라우저가 다르기때문 , 브라우저당 세션 값이 달라진다
        // 같은 브라우저라도 사용자(계정)가 다르면 세션값이 틀림
    %>

    <h2>세션 테스트</h2>
    <ul>
        <li>isNew() : <%=session.isNew() %></li>
        <li>생성시간 : <%=session.getCreationTime() %></li>
        <li>최종접속시간 : <%=session.getLastAccessedTime() %></li>
        <li>세션ID : <%=session.getId() %></li>
    </ul>
</body>
</html>