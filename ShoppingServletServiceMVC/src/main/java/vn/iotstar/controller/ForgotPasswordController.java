package vn.iotstar.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.service.IUserService;
import vn.iotstar.service.impl.UserService;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = {"/forgot"})
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(Constant.Path.FORGOT).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String newPass = req.getParameter("newPassword");
        String confirm = req.getParameter("confirmPassword");

        String alert = null;

        if (username == null || email == null || newPass == null || confirm == null
                || username.isBlank() || email.isBlank() || newPass.isBlank() || confirm.isBlank()) {
            alert = "Vui lòng nhập đầy đủ thông tin.";
        } else if (!newPass.equals(confirm)) {
            alert = "Mật khẩu xác nhận không khớp.";
        } else if (!userService.matchUsernameEmail(username, email)) {
            alert = "Username và Email chưa khớp hệ thống.";
        }

        if (alert != null) {
            req.setAttribute("alert", alert);
            req.getRequestDispatcher(Constant.Path.FORGOT).forward(req, resp);
            return;
        }

        boolean ok = userService.resetPassword(username, newPass);
        if (ok) {
            req.getSession(true).setAttribute("flash", "Đổi mật khẩu thành công. Mời đăng nhập lại!");
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.setAttribute("alert", "Có lỗi hệ thống khi đổi mật khẩu.");
            req.getRequestDispatcher(Constant.Path.FORGOT).forward(req, resp);
        }
    }
}
