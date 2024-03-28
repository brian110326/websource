package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import dto.SearchDto;
import service.BoardService;
import service.BoardServiceImpl;

public class BoardSearchAction implements Action {

    private String path;

    public BoardSearchAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        BoardService service = new BoardServiceImpl();

        String criteria = req.getParameter("criteria");
        String keyword = req.getParameter("keyword");

        SearchDto searchDto = new SearchDto();
        searchDto.setCriteria(criteria);
        searchDto.setKeyword(keyword);

        List<BoardDto> list = service.searchList(searchDto);

        req.setAttribute("list", list);
        return new ActionForward(path, false);
    }

}
