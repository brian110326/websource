package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.MemberDto;
import service.BookService;
import service.BookServieImpl;

public class BookLoginAction implements Action {

    private String path;

    public BookLoginAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String userid = req.getParameter("userid");
        String password = req.getParameter("password");

        BookService service = new BookServieImpl();

        MemberDto dto = new MemberDto();
        dto.setUserid(userid);
        dto.setPassword(password);

        MemberDto loginDto = service.login(dto);

        HttpSession session = req.getSession();

        if (loginDto != null) {
            session.setAttribute("loginDto", loginDto);
            // session에 저장만 하고 다른 화면에 아이디 비밀번호를 보여주지 않으니까 set으로 저장만
        } else {
            path = "/view/login.jsp";
        }

        return new ActionForward(path, true);
    }

}
