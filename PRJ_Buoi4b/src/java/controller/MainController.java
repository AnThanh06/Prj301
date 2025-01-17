
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.print.DocFlavor;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    public boolean isValidLogin(String username, String password){
        return username.equals("admin") && password.equals("12345678");
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String txtUsername = request.getParameter("txtUsername");
        String txtPassword = request.getParameter("txtPassword");
        
        
        if(txtUsername ==null || txtUsername.trim().length()==0){
            out.println("please enter Username");
            return;
        }
        

        
        if(txtPassword == null || txtPassword.trim().length() <8 ){
            out.println("Not enought 8 chacracters");
            return;
        }
        
        
        // login process
        //if(txtUsername.equals("admin") && txtPassword.equals("12345678")){
        //    out.println("login successfull !!!!");
        //    return;
            // forward: chuyển tiếp
            // redirec: chuyển hướng
       // }else{
       //     out.println("invalid user name or password");
        //    return;
       // }
       
       if(isValidLogin(txtUsername, txtPassword)){
           //forward , redirect
           // Công cụ giúp đển chuyển 
           RequestDispatcher rd = request.getRequestDispatcher("search.html");
           rd.forward(request, response);
       }else{
           //forward
           //RequestDispatcher rd = request.getRequestDispatcher("invalid.html");
           //rd.forward(request,response);
           
           
           // redirect : dùng để chuyển hướng 1 cái trang khác với chuyển tiếp ở trên
           response.sendRedirect("invalid.html");
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
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
