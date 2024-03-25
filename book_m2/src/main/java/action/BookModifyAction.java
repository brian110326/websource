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

        // ==> code, price처럼 getParameter로 가져온것은 객체로 보내지 X
        // ==> but 만약에 code, price로 DB작업(insert, update...)을 해서 결정이 된 상태라면 객체로 보내기 가능
        // 객체로 보낼지 말지 여부를 결정할 때는 일단 입력값이 잘못되었을 때를 생각해보기

        return new ActionForward(path, true);
    }

}
