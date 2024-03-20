package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dto.BookDto;

@WebServlet("/modify")
public class BookModifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        BookDao dao = new BookDao();
        BookDto dto = new BookDto();

        // getParameter("code") ==> code가 어떤 부분인지
        // name요소를 가져오는 것도 있지만 read.jsp 주소줄에 ~code=${dto.code} 앞부분의 code
        int code = Integer.parseInt(req.getParameter("code"));
        int price = Integer.parseInt(req.getParameter("price"));

        int result = 0;

        dto.setCode(code);
        dto.setPrice(price);

        if (code == dto.getCode()) {
            result = dao.update(dto);
            resp.sendRedirect("list");
        } else {
            resp.sendRedirect("/view/modify.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
