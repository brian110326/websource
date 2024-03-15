package scope;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/scope")
public class ScopeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // forward
        // forward 시켜서 어떤 페이지로 갈것인지 명시
        RequestDispatcher rd = req.getRequestDispatcher("/scope/forward.jsp");
        // ScopeServlet의 req를 forward.jsp에게 넘긴다
        rd.forward(req, res);

        // Servlet은 java코드가 편하고 JSP는 html이 편함
        // DB랑도 연동을 해야하기 때문에 java코드가 필요함
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
