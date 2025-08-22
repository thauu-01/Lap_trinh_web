package trunghau.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/createcookie"})
public class CreateCookie extends HttpServlet {
    private static final long serialVersionUID = 1L; // 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String ten = request.getParameter("ten");
        String holot = request.getParameter("holot");


        Cookie firstName = new Cookie("ten", ten != null ? ten : "");
        Cookie lastName = new Cookie("holot", holot != null ? holot : "");


        firstName.setMaxAge(60 * 60 * 24);
        lastName.setMaxAge(60 * 60 * 24);

        response.addCookie(firstName);
        response.addCookie(lastName);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h2>Cookie Created Successfully!</h2>");
            out.println("<b>First Name</b>: " + firstName.getValue() + " - <b>Last Name</b>: " + lastName.getValue());
            out.println("<br/><a href='readcookie'>Check Cookies</a>");
            out.println("<br/><a href='deletecookie'>Delete Cookie 'ten'</a>");
            out.println("</body></html>");
        }
    }
}