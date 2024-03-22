package action;

import javax.servlet.http.HttpServletRequest;

import service.BookService;
import service.BookServieImpl;

public class BookDeleteAction implements Action {

    private String path;

    public BookDeleteAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String code = req.getParameter("code");
        BookService service = new BookServieImpl();
        boolean result = service.delete(Integer.parseInt(code));

        return new ActionForward(path, true);
    }

}
