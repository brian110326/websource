package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.MemberDto;
import service.BookService;
import service.BookServieImpl;

public class BookRegisterAction implements Action {

    private String path;

    public BookRegisterAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        // register.jsp에서 넘긴 값 가져오기
        String userid = req.getParameter("userid");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        BookService service = new BookServieImpl();
        HttpSession session = req.getSession();

        MemberDto dto = new MemberDto(userid, password, name, email);
        if (!service.register(dto)) {
            path = "/view/register.jsp";
        }

        return new ActionForward(path, true);

    }

}
