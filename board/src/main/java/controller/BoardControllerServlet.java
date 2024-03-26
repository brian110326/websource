package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;

@WebServlet("*.do")
// *.do : 예를 들어 도서목록을 누르면 list.do로 가라고 작성을 함 ==> list.do이 servlet페이지로 와서 할 작업을
// 하게됨
public class BoardControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 한글처리
        req.setCharacterEncoding("utf-8");

        // URI 분리
        String requestUri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String cmd = requestUri.substring(contextPath.length());

        // cmd를 가지고 action 생성
        Action action = null;

        // 생성된 action에게 일 시키기(원래는 서블렛이 해야했던 일)
        ActionForward af = null;
        try {
            af = action.execute(req);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 이동방식과 경로에 따라 움직이기
        if (af.isRedirect()) {
            resp.sendRedirect(af.getPath());
        } else {
            RequestDispatcher rd = req.getRequestDispatcher(af.getPath());
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}