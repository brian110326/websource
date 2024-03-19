<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%!
    private String getCookieValue(Cookie[] cookies, String key){
        if(cookies == null){
            return null;
        }
        String name = "";
        String value = ""
        for(Cookie c : cookie){
            if(c.getName().equals(key)){
                name = c.getName();
                value = c.getValue();
                return c.getValue();
            }
        }
    }
%>

<%
    Cookie[] cookies = request.getCookie();
%>

<h3>쿠키 이름 : <%=%> 값 : <%=getCookieValue(cookies, "name")%></h3>
<h3>성별 : <%=getCookieValue(cookies, "gender")%></h3>
<h3>나이 : <%=getCookieValue(cookies, "age")%></h3>