package action;

import javax.servlet.http.HttpServletRequest;

import dto.TodoDto;
import service.TodoService;
import service.TodoServiceImpl;

public class TodoReadAction implements Action {

    private String path;

    public TodoReadAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String no = req.getParameter("no");
        TodoService service = new TodoServiceImpl();
        TodoDto dto = service.getRow(no);

        req.setAttribute("todo", dto);

        return new ActionForward(path, false);
    }

}
