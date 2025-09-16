package vn.iotstar;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/loginSession"})
public class LoginSessionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("phuongthi".equals(username) && "123".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("name", username);

            response.sendRedirect(request.getContextPath() + "/ProfileServlet");
        } else {
            response.getWriter().print("Tài khoản hoặc mật khẩu không chính xác");
            request.getRequestDispatcher("loginSession.html").include(request, response);
        }
    }
}
