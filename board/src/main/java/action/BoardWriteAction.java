package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.BoardDto;
import service.BoardService;
import service.BoardServiceImpl;

public class BoardWriteAction implements Action {

    private String path;

    public BoardWriteAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String name = req.getParameter("name");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String password = req.getParameter("password");
        String attatch = req.getParameter("attatch");

        BoardService service = new BoardServiceImpl();
        BoardDto insertDto = new BoardDto();
        insertDto.setName(name);
        insertDto.setTitle(title);
        insertDto.setContent(content);
        insertDto.setPassword(password);
        insertDto.setAttatch(attatch);

        if (!service.create(insertDto)) {
            path = "/view/qna_board_write.jsp";
        }

        return new ActionForward(path, true);
    }

}
