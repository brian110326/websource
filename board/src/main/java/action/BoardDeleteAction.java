package action;

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
        BoardService service = new BoardServiceImpl();
        BoardDto deleteDto = new BoardDto();

        deleteDto.setBno(Integer.parseInt(bno));
        deleteDto.setPassword(password);

        if (!service.delete(deleteDto)) {
            path = "/view/qna_board_pwdCheck.jsp?bno=" + deleteDto.getBno();
        }

        return new ActionForward(path, true);
    }

}
