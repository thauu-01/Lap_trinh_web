package trunghau.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/hello", "/xin-chao"})
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession(false); 
        String name = "";

        if (session != null && session.getAttribute("username") != null) {
            name = (String) session.getAttribute("username");
        }

        if (name.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/login.html");
        } else {
            try (PrintWriter printWriter = resp.getWriter()) {
                printWriter.println("Xin chào " + name);
                printWriter.println("<br/><a href='index.html'>Quay lại</a>");
                printWriter.println("<br/><a href='logout'>Đăng xuất</a>");
            }
        }
    }
}