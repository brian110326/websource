package cart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addCart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        // 사용자가 선택한 값을 가져오기 + 세션 담기 + 페이지 이동(basket.jsp)
        String product = req.getParameter("product");

        HttpSession session = req.getSession();
        // session.setAttribute("product", product);
        // 장바구니 세션 존재 여부 확인
        ArrayList<String> products = (ArrayList<String>) session.getAttribute("products");
        if (products == null) {
            // 장바구니 세션을 새로 만들기
            products = new ArrayList<>();
            products.add(product);
            // 넣고 뭐라도 하나 있어야 화면에 보여지기 때문에 add된 상태에서 다음에 setAttriubute
            session.setAttribute("products", products);

        } else {
            // 장바구니가 존재한다면 상품만 추가
            products.add(product);
        }

        resp.sendRedirect("/cart/basket.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
