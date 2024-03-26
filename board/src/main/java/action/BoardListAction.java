package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import service.BoardService;
import service.BoardServiceImpl;

public class BoardListAction implements Action {

    private String path;

    public BoardListAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        BoardService service = new BoardServiceImpl();
        List<BoardDto> list = service.list();
        req.setAttribute("list", list);
        return new ActionForward(path, false);
    }

}
