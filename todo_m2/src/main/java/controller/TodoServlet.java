package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import action.TodoListAction;
import dao.TodoDao;
import dto.TodoDto;
import service.TodoService;
import service.TodoServiceImpl;

@WebServlet("*.do")
public class TodoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        // 경로에서 요청 찾기
        String requestUri = req.getRequestURI(); // 8080 이후의 값
        String contextPath = req.getContextPath(); // 프로젝트명(todo_m2)
        String cmd = requestUri.substring(contextPath.length()); // /create.do
        // 어디에서 온 요청인지 알아내기 위해 cmd가 필요(insert, create, modify..어디인지)

        // System.out.println("requestUri : " + requestUri);
        // System.out.println("contextPath : " + contextPath);
        System.out.println("cmd : " + cmd);

        TodoDao dao = new TodoDao();
        TodoService service = new TodoServiceImpl();

        Action action = null;
        if (cmd.equals("/list.do")) {
            // TodoListServlet에서 했던 작업
            action = new TodoListAction("/view/list.jsp");

            // RequestDispatcher rd = req.getRequestDispatcher("/view/list.jsp");
            // rd.forward(req, resp);
        } else if (cmd.equals("/read.do")) {
            // TodoReadServlet에서 했던 작업
            String no = req.getParameter("no");

            TodoDto dto = service.getRow(no);
            // TodoDto dto = dao.getRow(no);
            req.setAttribute("todo", dto);

            // RequestDispatcher rd = req.getRequestDispatcher("/view/read.jsp");
            // rd.forward(req, resp);
        } else if (cmd.equals("/modify.do")) {
            String no = req.getParameter("no");

            TodoDto dto = service.getRow(no);
            // TodoDto dto = dao.getRow(no);
            req.setAttribute("todo", dto);

            // RequestDispatcher rd = req.getRequestDispatcher("/view/modify.jsp");
            // rd.forward(req, resp);
        } else if (cmd.equals("/update.do")) {
            String completed = req.getParameter("completed");
            String description = req.getParameter("description");
            String no = req.getParameter("no");

            // DB작업

            TodoDto dto = new TodoDto();
            dto.setCompleted(Boolean.parseBoolean(completed));
            dto.setDescription(description);
            dto.setNumber(Integer.parseInt(no));

            boolean result = service.update(dto);
            // int result = dao.update(dto);

            // servlet의 별칭 list 경로로 이동
            // resp.sendRedirect("/list.do");
        } else if (cmd.equals("/delete.do")) {
            String no = req.getParameter("no");

            // DB작업
            boolean result = service.delete(no);
            // int result = dao.delete(no);

            // 화면이동
            // resp.sendRedirect("/list.do");
        } else if (cmd.equals("/create.do")) {
            String title = req.getParameter("title");
            String description = req.getParameter("description");

            // DB작업 insert
            TodoDto insertdto = new TodoDto();

            insertdto.setTitle(title);
            insertdto.setDescription(description);

            boolean result = service.insert(insertdto);
            // int result = dao.insert(insertdto);

            // resp.sendRedirect("/list.do");
        }

        ActionForward af = null;

        try {
            af = action.execute(req);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
