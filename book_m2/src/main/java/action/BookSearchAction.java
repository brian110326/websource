package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.BookDto;
import service.BookService;
import service.BookServieImpl;

public class BookSearchAction implements Action {

    private String path;

    public BookSearchAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String criteria = req.getParameter("criteria");
        String keyword = req.getParameter("keyword");
        BookDto dto = new BookDto();
        BookService service = new BookServieImpl();

        List<BookDto> list = service.searchListAll(criteria, keyword);

        // 이것도 사용자 입력값이니 보내면 안될 것 같은 느낌...
        // if문으로 나눠서 코드면 path += "?code=" + dto.getCode()
        req.setAttribute("list", list);

        // if (criteria.equals("code")) {
        // dto.setCode(Integer.parseInt(criteria));
        // path += "?code=" + dto.getCode();
        // } else if (criteria.equals("writer")) {
        // dto.setWriter(criteria);
        // path += "?writer=" + dto.getWriter();
        // }

        return new ActionForward(path, false);
    }

}
