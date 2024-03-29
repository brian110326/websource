package action;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import service.BoardService;
import service.BoardServiceImpl;

public class BoardDeleteAction implements Action {

    private String path;

    public BoardDeleteAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String password = req.getParameter("password");
        String bno = req.getParameter("bno");

        String page = req.getParameter("page");
        String amount = req.getParameter("amount");
        String criteria = req.getParameter("criteria");
        String keyword = URLEncoder.encode(req.getParameter("keyword"), "utf-8");

        BoardService service = new BoardServiceImpl();
        BoardDto deleteDto = new BoardDto();

        deleteDto.setBno(Integer.parseInt(bno));
        deleteDto.setPassword(password);

        if (!service.delete(deleteDto)) {
            path = "/view/qna_board_pwdCheck.jsp?bno=" + deleteDto.getBno() + "&page=" + page + "&amount=" + amount
                    + "&criteria=" + criteria + "&keyword="
                    + keyword;
        } else {
            path += "?page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword="
                    + keyword;
        }

        return new ActionForward(path, true);
    }

}
