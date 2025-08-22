package trunghau.demo.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String user = req.getParameter("username");
        String pass = req.getParameter("password");
        String authType = req.getParameter("authType");

        if (user != null && user.equals("hau") && pass != null && pass.equals("123")) {
            if ("session".equals(authType)) {
        
                HttpSession session = req.getSession();
                session.setAttribute("username", user);
                session.setMaxInactiveInterval(30 * 60); 
            } else if ("cookie".equals(authType)) {
          
                Cookie usernameCookie = new Cookie("username", user);
                usernameCookie.setMaxAge(60 * 60 * 24); 
                resp.addCookie(usernameCookie);
            }
            resp.sendRedirect(req.getContextPath() + "/hello");
        } else {
            req.getRequestDispatcher("/login.html").forward(req, resp);
        }
    }
}