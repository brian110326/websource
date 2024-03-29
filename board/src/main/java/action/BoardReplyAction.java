package action;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import dto.SearchDto;
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

        String page = req.getParameter("page");
        String amount = req.getParameter("amount");
        String criteria = req.getParameter("criteria");
        String keyword = URLEncoder.encode(req.getParameter("keyword"), "utf-8");

        BoardDto replyDto = new BoardDto();
        replyDto.setName(name);
        replyDto.setTitle(title);
        replyDto.setContent(content);
        replyDto.setPassword(password);

        replyDto.setReRef(Integer.parseInt(reRef));
        replyDto.setReLev(Integer.parseInt(reLev));
        replyDto.setReSeq(Integer.parseInt(reSeq));

        if (!service.reply(replyDto)) {
            // path 설정할 때 최종적으로 jsp파일로 가야한다면
            // 중간에 .do파일을 거치는지 여부를 꼭 확인하기
            path = "/qReplyView.do?bno=" + bno + "&page=" + page + "&amount=" + amount + "&criteria=" + criteria
                    + "&keyword="
                    + keyword;
        } else {
            path += "?page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword="
                    + keyword;
        }

        return new ActionForward(path, true);
    }

}
