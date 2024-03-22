package action;

import javax.servlet.http.HttpServletRequest;

import dto.BookDto;
import service.BookService;
import service.BookServieImpl;

public class BookCreateAction implements Action {

    private String path;

    public BookCreateAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String code = req.getParameter("code");
        String title = req.getParameter("title");
        String writer = req.getParameter("writer");
        String price = req.getParameter("price");
        String description = req.getParameter("description");

        System.out.println(description);

        BookService service = new BookServieImpl();
        BookDto dto = new BookDto(Integer.parseInt(code), title, writer, Integer.parseInt(price), description);

        boolean result = service.create(dto);

        return new ActionForward(path, true);
    }

}
