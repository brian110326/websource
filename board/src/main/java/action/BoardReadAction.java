package action;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import dto.SearchDto;
import service.BoardService;
import service.BoardServiceImpl;

public class BoardReadAction implements Action {

    private String path;

    public BoardReadAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String bno = req.getParameter("bno");
        BoardService service = new BoardServiceImpl();

        String page = req.getParameter("page");
        String amount = req.getParameter("amount");
        String criteria = req.getParameter("criteria");
        String keyword = req.getParameter("keyword");
        SearchDto searchDto = new SearchDto(Integer.parseInt(page), Integer.parseInt(amount), criteria, keyword);

        BoardDto dto = service.getRow(Integer.parseInt(bno));

        req.setAttribute("dto", dto);
        req.setAttribute("searchDto", searchDto);

        return new ActionForward(path, false);
    }

}
