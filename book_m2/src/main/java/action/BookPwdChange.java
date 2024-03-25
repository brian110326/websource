package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.ChangeDto;
import dto.MemberDto;
import service.BookService;
import service.BookServieImpl;

public class BookPwdChange implements Action {

    private String path;

    public BookPwdChange(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        // 아이디와 현재비밀번호를 가지고 있는지 확인(서비스 메소드 호출)

        // false면 pwdChange.jsp로 이동

        BookService service = new BookServieImpl();

        String password = req.getParameter("password");
        String newPassword = req.getParameter("new-password");
        String confirmPassword = req.getParameter("confirm-password");

        HttpSession session = req.getSession();
        MemberDto loginDto = (MemberDto) session.getAttribute("loginDto");

        // session에 담긴 아이디와 사용자가 입력한 현재비밀번호를 가지고 있는 객체
        MemberDto input = new MemberDto();
        input.setUserid(loginDto.getUserid());
        input.setPassword(password);

        if (service.login(input) == null) {
            // "현재" 비밀번호가 틀린 경우
            path = "/view/pwdChange.jsp";
        } else {
            // 사용자가 존재한다면 비밀번호 변경 서비스 메소드 호출
            ChangeDto changeDto = new ChangeDto();
            changeDto.setNewPassword(newPassword);
            changeDto.setUserid(loginDto.getUserid());

            if (service.change(changeDto)) {
                // 변경 완료 여부에 따라 true이면 세션 제거 후 로그인 페이지로 이동
                session.invalidate();
            } else {
                path = "/view/pwdChange.jsp";
            }
        }

        // newPassword, confrimPassword 같을때 다를때 메소드 googledrive에서 확인해보기

        return new ActionForward(path, true);
    }

}
