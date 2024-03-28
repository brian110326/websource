package action;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import service.BoardService;
import service.BoardServiceImpl;

public class BoardUpdateCountAction implements Action {

    private String path;

    public BoardUpdateCountAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String bno = req.getParameter("bno");
        BoardService service = new BoardServiceImpl();

        service.updateCount(Integer.parseInt(bno));

        path += "?bno=" + bno;

        // 새로고침할때마다 조회수 증가 방지

        return new ActionForward(path, true);
    }

}
