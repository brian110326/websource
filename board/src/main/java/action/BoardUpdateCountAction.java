package action;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import dto.SearchDto;
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

        // 페이지 나누기 개념 추가 후
        String page = req.getParameter("page");
        String amount = req.getParameter("amount");
        String criteria = req.getParameter("criteria");

        // 한글일 경우 get 방식으로 넘어올 때 깨짐 ==> encoding
        String keyword = URLEncoder.encode(req.getParameter("keyword"), "utf-8");

        service.updateCount(Integer.parseInt(bno));

        path += "?bno=" + bno + "&page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword=" + keyword;

        // 새로고침할때마다 조회수 증가 방지

        return new ActionForward(path, true);
    }

}
