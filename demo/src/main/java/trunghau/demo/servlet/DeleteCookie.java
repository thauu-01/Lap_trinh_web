package trunghau.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/deletecookie"})
public class DeleteCookie extends HttpServlet {
    private static final long serialVersionUID = 1L; 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            if (cookies != null) {
                out.println("<h2>Cookies Name and Value</h2>");
                for (Cookie cookie : cookies) {
                    if ("ten".equals(cookie.getName())) {
                        cookie.setMaxAge(0); 
                        response.addCookie(cookie);
                        out.print("Deleted cookie: " + cookie.getName() + "<br/>");
                    }
                    out.print("Name: " + cookie.getName() + ", ");
                    out.print("Value: " + cookie.getValue() + "<br/>");
                }
            } else {
                out.println("<h2>No cookies found</h2>");
            }
            out.println("<br/><a href='index.html'>Back to Form</a>");
        }
    }
}