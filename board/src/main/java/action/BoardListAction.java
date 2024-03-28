package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import dto.PageDto;
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

        String criteria = req.getParameter("criteria");
        String keyword = req.getParameter("keyword");

        SearchDto searchDto = new SearchDto(page, amount, criteria, keyword);
        PageDto pageDto = new PageDto(searchDto, service.getRows(criteria, keyword));

        List<BoardDto> list = service.list(searchDto);

        req.setAttribute("list", list);
        req.setAttribute("pageDto", pageDto); // searchDto 포함됨

        return new ActionForward(path, false);
    }

}
