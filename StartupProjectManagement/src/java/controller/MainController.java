
package controller;

import dao.UserDAO;
import com.sun.corba.se.spi.protocol.RequestDispatcherDefault;
import dto.UserDTO;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "MainController", urlPatterns = {"/MainController"})

public class MainController extends HttpServlet {
    
    private static final String Login_Page = "Login.jsp"; 
    
    public UserDTO getUser(String strUsername){
        UserDAO udao = new UserDAO();
        UserDTO user = udao.readById(strUsername);
        return user;
        
    }
    public boolean isValidLogin(String strUsername, String strPassword) {
    UserDTO user = getUser(strUsername);
    if (user == null) return false;
    
    // So sánh mật khẩu an toàn, tránh null
    String dbPassword = user.getPassword();
    return dbPassword != null && dbPassword.equals(strPassword);
}
    
    

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = Login_Page;
         try {
            String action = request.getParameter("action");
            System.out.println("action: "+ action);
            if (action == null) {
                url = Login_Page;
            } else {
                if (action.equals("Login")) {
                    String strUsername = request.getParameter("txtUsername");
                    String strPassword = request.getParameter("txtPassword");
                    if(isValidLogin(strUsername, strPassword)){
                        url ="search.jsp";
                        UserDTO user = getUser(strUsername);
                        request.setAttribute("user", user);
                    }else{
                        url ="Invalid.jsp";
                    }
                }else if(action.equals("logout")){
                    request.setAttribute("user", null);
                    url = "logout_confirm.jsp";
                }
            }
        } catch (Exception e) {
             log("Error at MainController: " + e.toString());

        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
    }

}
