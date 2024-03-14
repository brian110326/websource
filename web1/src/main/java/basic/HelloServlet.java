package basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// HttpServletRequest : 사용자의 요청을 가져오는 객체
// HttpServletResponse : 사용자에게 응답할 때 사용하는 객체

@WebServlet("/hello") // servlet 별칭
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // form이 get인 경우

        // post방식의 한글은 깨짐
        // 가져오는 모든 데이터는 String 형태
        // req.getParameter("form 요소명")

        // post 방식으로 넘어오는 한글 인코딩 처리 ==> getParameter 전에해야함
        req.setCharacterEncoding("utf-8");

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        // value가 여러개인 checkbox같은 경우 ==> 값 가져오는 방법이 다름
        String[] dogs = req.getParameterValues("dog");

        // 응답할 페이지 설정 : html파일로 응답하고 utf-8로 인코딩할것이다
        res.setContentType("text/html;charset=utf-8");
        PrintWriter out = res.getWriter();
        out.print("<ul>");
        out.print("<li>id : " + id + "</li>");
        out.print("<li>name : " + name + "</li>");
        for (String dog : dogs) {
            out.print("<li>dog : " + dog + "</li>");
        }
        out.print("</ul>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // form이 post인 경우
        doGet(req, res);
    }
}
