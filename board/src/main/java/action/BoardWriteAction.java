package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dto.BoardDto;
import service.BoardService;
import service.BoardServiceImpl;

public class BoardWriteAction implements Action {

    private String path;

    public BoardWriteAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String name = req.getParameter("name");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String password = req.getParameter("password");
        String attatch = req.getParameter("attatch");

        BoardService service = new BoardServiceImpl();
        BoardDto insertDto = new BoardDto();
        insertDto.setName(name);
        insertDto.setTitle(title);
        insertDto.setContent(content);
        insertDto.setPassword(password);

        // 파일 업로드 처리
        Part part = req.getPart("attatch");
        String fileName = getFileName(part);

        if (!fileName.isEmpty()) {
            part.write(fileName);
            insertDto.setAttatch(fileName);
        }

        if (!service.create(insertDto)) {
            path = "/view/qna_board_write.jsp";
        }

        return new ActionForward(path, true);
    }

    private String getFileName(Part part) {
        String header = part.getHeader("content-disposition");

        String[] arr = header.split(";");

        for (int i = 0; i < arr.length; i++) {
            String temp = arr[i];

            if (temp.trim().startsWith("filename")) {
                return temp.substring(temp.indexOf("=") + 2, temp.length() - 1);
            }
        }

        return "";
    }

}
