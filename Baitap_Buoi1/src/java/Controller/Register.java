/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thanh
 */
@WebServlet(name = "Register", urlPatterns = {"/Regis"})
public class Register extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Register</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String txtUsername = request.getParameter("txtUsername");
        String txtPassword = request.getParameter("txtPassword");
        String txtEmail = request.getParameter("txtEmail");
        String txtConpassword = request.getParameter("txtConpassword");
        
        if(txtUsername == null || txtUsername.trim().isEmpty()) {
            out.println("Username không được để trống!");
            return;
        }
        
        if(txtPassword == null || txtPassword.trim().isEmpty()) {
            out.println("Password không được để trống!");
            return;
        }
        
        if(txtConpassword == null || txtConpassword.trim().isEmpty()) {
            out.println("Confirm password không được để trống!");
            return;
        }
        
        if(txtEmail == null || txtEmail.trim().isEmpty()) {
            out.println("Email không được để trống!");
            return;
        }
        
        
        if(!txtPassword.equals(txtConpassword)) {
            out.println("Password và Confirm password phải giống nhau!");
            return;
        }
        
        
        
        out.println("Đăng ký thành công!");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
