package vn.iotstar;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/ProfileServlet"})
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("name") != null) {
            String name = (String) session.getAttribute("name");
            out.print("Chào bạn, " + name + " đến với trang quản lý tài khoản <br>");
            out.print("<a href='" + request.getContextPath() + "/LogoutServlet'>Đăng xuất</a>");
        } else {

            response.sendRedirect(request.getContextPath() + "/loginSession.html");
        }
    }
}
