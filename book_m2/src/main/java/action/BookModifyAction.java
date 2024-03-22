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

        return new ActionForward(path, true);
    }

}
