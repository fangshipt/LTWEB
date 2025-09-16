package vn.iotstar;

import java.io.IOException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        if ("Thi".equals(user) && "123".equals(pass)) {
            Cookie cookie = new Cookie("username", user);
            cookie.setMaxAge(30);
            resp.addCookie(cookie);
            resp.sendRedirect(req.getContextPath() + "/hello");
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.html");
        }

    }
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.html");
    }
}

