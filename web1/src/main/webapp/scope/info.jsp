<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%
    // info.html, info.js, info.jsp, next.jsp 같이보기
    //id, name, age 가져오기
    request.setCharacterEncoding("utf-8");
    String id = request.getParameter("id");
    String name = request.getParameter("name");
    int age = Integer.parseInt(request.getParameter("age"));

    // HttpServletRequest : 유효범위가 제한적
    // request.getParameter() : 사용자의 입력값을 가져올 때 범위가 제한 됨

    // 제한이 form action의 값으로 사용된 info.jsp 페이지까지만 가능
    // info.jsp가 알고 있는 값(사용자입력값, DB값)을 다른 페이지랑도 공유
    
    // 1) get/post 방식으로 넘겨준다 , get방식 : ?키&value값 but 키와 value가 아이디와 비밀번호라면?
    // 중요한 정보는 뜨게되면 X

    // 2) scope 이용
    // (1) page : 현재 page(X)
    // (2) request : HttpServletRequest의 유효범위와 동일(O)
    // (3) session : HttpSession의 유효범위와 동일(O)(브라우저를 닫기 전까지 유효범위가 살아있음)
    // (4) application : 톰캣 서버의 범위(X)

    // ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
    // request.setAttribute("key",value), request.getAttribute("key") ==> scope 전용 메소드
    // session.setAttribute("key",value), session.getAttribute("key")

%>
<h3>id : <%=id%></h3>
<h3>name : <%=name%></h3>
<h3>age : <%=age%></h3>

<%
  // request scope 사용
  request.setAttribute("id",id);
  request.setAttribute("name",name);
  request.setAttribute("age",age);

  // next.jsp 화면이 보여지지만 info.js주소줄
  // forward가 데이터를 다음페이지까지 가져다줌
  // forward : 주소 != 내용
  // 각각의 jsp파일에는 req, res존재, forward시킬시 req를 동일하게 만들어버림
  // info.jsp에 부여된 request를 next.jsp에게 넘겨주는 것
  // info.jsp에서 할수 있는 작업들을 next.jsp에서 할 수 있게 됨
  pageContext.forward("next.jsp");

  // session.setAttribute("id",id);
  // session.setAttribute("name",name);
  // session.setAttribute("age",age);

  // session. set, get으로만 하기
  // request와 pageContext.forward 하기
%>

<%-- post 방식으로 직접 action에 담기 --%>
<%-- <form action="next.jsp" method="post">
      <div>
        <label for="id">id</label>

        <input type="text" name="id" id="id" value="<%=id%>" readonly />
      </div>
      <div>
        <label for="name">name</label>
        <input type="text" name="name" id="name" value="<%=name%>" readonly />
      </div>
      <div>
        <label for="age">age</label>
        <input type="text" name="age" id="age" value="<%=age%>" readonly />
      </div>
      <div>
        <button type="submit">전송</button>
      </div>
</form> --%>


<%-- <h3> --%>
    <%-- get방식으로 직접 코드를 작성 --%>
    <%-- <a href="next.jsp?id=<%=id%>&name=<%=name%>&age=<%=age%>">다음 페이지</a>
</h3> --%>

<%-- <h3>
    <a href="next.jsp">다음 페이지</a>
</h3> --%>