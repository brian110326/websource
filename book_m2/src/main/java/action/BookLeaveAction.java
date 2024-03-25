package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.MemberDto;
import service.BookService;
import service.BookServieImpl;

public class BookLeaveAction implements Action {

    private String path;

    public BookLeaveAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String password = req.getParameter("password");
        BookService service = new BookServieImpl();

        MemberDto deleteDto = new MemberDto();
        deleteDto.setPassword(password);

        if (!service.leave(deleteDto)) {
            path = "/view/leave.jsp";
        }

        HttpSession session = req.getSession();

        session.invalidate();

        return new ActionForward(path, true);
    }

}
