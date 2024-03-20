package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TodoDao;
import dto.TodoDto;

@WebServlet("/create")
public class TodoCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // DB 연동
        req.setCharacterEncoding("utf-8");

        String title = req.getParameter("title");
        String description = req.getParameter("description");

        // DB작업 insert
        TodoDao dao = new TodoDao();
        TodoDto insertdto = new TodoDto();

        insertdto.setTitle(title);
        insertdto.setDescription(description);

        int result = dao.insert(insertdto);

        // 화면이동(list) : 위의 결과(데이터)를 같이 가져가는지 여부 확인
        // 같은 view 폴더에 있으니 list.jsp만 작성
        // 다른 폴더에 존재한다면 => /폴더명/파일명.jsp

        resp.sendRedirect("list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
