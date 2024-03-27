package action;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import service.BoardService;
import service.BoardServiceImpl;

public class BoardModifyAction implements Action {

    private String path;

    public BoardModifyAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        BoardService service = new BoardServiceImpl();
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        BoardDto updateDto = new BoardDto();
        // NumberFormatException : Cannot parse null string
        updateDto.setBno(Integer.parseInt(req.getParameter("bno")));
        updateDto.setTitle(title);
        updateDto.setContent(content);
        updateDto.setPassword(req.getParameter("password"));

        if (!service.update(updateDto)) {
            // 바로 modify.jsp로 안간 이유 : 바로 jsp파일로 가면 dto.name처럼 담은게 없으니까
            // 그래서 qRead.do를 통해 다시 객체를 담고 해야 그래야 다시 value값이 보이게됨
            // 그냥 modify.jsp로 가본다면 화면 보기
            path = "/qModify.do?bno=" + updateDto.getBno();
            // path = "/qna_board_modify.jsp";
        } else {
            // controller에 적어둔 path = /qRead.do
            path += "?bno=" + updateDto.getBno();
        }

        // ifelse구문에 path에 각각 ?bno를 붙이는이유 : 둘다 path를 통해 BoardReadAction으로 작업을 수행함
        // BoardReadAction에서 bno를 getParameter로 가져왔으므로 여기에서 bno를 주소에 설정해줘야함

        return new ActionForward(path, true);
    }

}
