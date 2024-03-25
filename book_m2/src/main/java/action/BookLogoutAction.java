package action;

import javax.servlet.http.HttpServletRequest;

public class BookLogoutAction implements Action {

    private String path;

    public BookLogoutAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        return new ActionForward(path, false);
    }

}
