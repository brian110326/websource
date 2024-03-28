package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import dto.SearchDto;
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
        int page = Integer.parseInt(req.getParameter("page"));
        int amount = Integer.parseInt(req.getParameter("amount"));

        SearchDto searchDto = new SearchDto(0, 0);
        searchDto.setAmount(amount);
        searchDto.setPage(page);

        List<BoardDto> list = service.list(searchDto);
        req.setAttribute("list", list);
        return new ActionForward(path, false);
    }

}
