package action;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import service.BoardService;
import service.BoardServiceImpl;

public class BoardReplyAction implements Action {

    private String path;

    public BoardReplyAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        BoardService service = new BoardServiceImpl();
        String name = req.getParameter("name");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String password = req.getParameter("password");

        String reRef = req.getParameter("reRef");
        String reLev = req.getParameter("reLev");
        String reSeq = req.getParameter("reSeq");
        String bno = req.getParameter("bno");

        BoardDto replyDto = new BoardDto();
        replyDto.setName(name);
        replyDto.setTitle(title);
        replyDto.setContent(content);
        replyDto.setPassword(password);

        replyDto.setReRef(Integer.parseInt(reRef));
        replyDto.setReLev(Integer.parseInt(reLev));
        replyDto.setReSeq(Integer.parseInt(reSeq));

        if (!service.reply(replyDto)) {
            path = "/qReplyView.do?bno=" + bno;
        }

        return new ActionForward(path, true);
    }

}
