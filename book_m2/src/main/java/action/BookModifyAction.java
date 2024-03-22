package action;

import javax.servlet.http.HttpServletRequest;

import dto.BookDto;
import service.BookService;
import service.BookServieImpl;

public class BookModifyAction implements Action {

    private String path;

    public BookModifyAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {

        String code = req.getParameter("code");
        String price = req.getParameter("price");

        BookService service = new BookServieImpl();
        BookDto dto = new BookDto();

        dto.setCode(Integer.parseInt(code));
        dto.setPrice(Integer.parseInt(price));

        boolean result = service.update(dto);

        if (result) {
            // Servlet파일에서 path => read.do
            path += "?code=" + dto.getCode();
        } else {
            path = "/view/modify.jsp";
        }

        // req.setAttribute("dto", dto);
        // 이렇게해도 작동은 되지만 사용자 입력값이 잘못입력했을때가 있을수가 있음
        // ==> 사용자 입력값은 객체로 다른 주소로 보내지 않는다. 이미 정해진 값만 확정된 값만 보낸다

        return new ActionForward(path, true);
    }

}
