package action;

import java.io.File;
import java.net.URLEncoder;
import java.util.UUID;

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

        String page = req.getParameter("page");
        String amount = req.getParameter("amount");
        String criteria = req.getParameter("criteria");
        String keyword = URLEncoder.encode(req.getParameter("keyword"), "utf-8");

        BoardService service = new BoardServiceImpl();
        BoardDto insertDto = new BoardDto();
        insertDto.setName(name);
        insertDto.setTitle(title);
        insertDto.setContent(content);
        insertDto.setPassword(password);

        // 파일 업로드 처리
        Part part = req.getPart("attach");
        String fileName = getFileName(part);

        // upload는 그냥 임의 생성한 폴더명
        String saveDir = "c:\\upload";

        if (!fileName.isEmpty()) {

            // 고유한 값 생성 ==> 고유한 값_사용자가 올린파일명
            // 중복파일명은 저장을 해주지 않으므로 ==> 서버에 저장 시 다른 이름을 사용해야하기 때문
            UUID uuid = UUID.randomUUID();
            // File.separator : \
            // File객체 다시 공부
            File uploadFile = new File(saveDir + File.separator + uuid + "_" + fileName);

            // c:\\upload\\1.jpg
            // part.write(saveDir + "\\" + fileName); // 서버의 디스크에 파일 저장
            part.write(uploadFile.toString()); // 서버의 디스크에 파일 저장

            // insertDto.setAttatch(fileName);
            insertDto.setAttatch(uploadFile.getName());
        }

        System.out.println(insertDto);

        if (!service.create(insertDto)) {
            path = "/view/qna_board_write.jsp" + "?page=" + page + "&amount=" + amount + "&criteria=" + criteria
                    + "&keyword="
                    + keyword;
        } else {
            path += "?page=" + page + "&amount=" + amount + "&criteria=" + criteria
                    + "&keyword="
                    + keyword;
        }

        return new ActionForward(path, true);
    }

    private String getFileName(Part part) {
        String header = part.getHeader("content-disposition");
        // Content-Disposition: attachment; filename="filename.jpg"

        String[] arr = header.split(";");

        for (int i = 0; i < arr.length; i++) {
            String temp = arr[i];

            if (temp.trim().startsWith("filename")) {
                // 파일이름만 추출
                return temp.substring(temp.indexOf("=") + 2, temp.length() - 1);
            }
        }

        return "";
    }

}
