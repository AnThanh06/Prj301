
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    public int GCD(int a, int b){
        // thầy nói hoàn thành trước hoàn hảo sau :)))
        int min = Math.min(a,b);
       for(int  i=min ; i>=1; i--){
           if(a%i==0 && b%i==0){
               return i;
           }
       }
       return 1;
        
    
    }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String txtA = request.getParameter("txtA");
        String txtB = request.getParameter("txtB");
        
        
        if(txtA == null||txtA.trim().length()==0){
            out.println("please enter A value: ");
            return ;
        }
        if(txtB == null ||txtB.trim().length()==0){
            out.println("please enter B value: ");
            return;
        }
         int a = 0;
         int b = 0;
        try {
            a = Integer.parseInt(txtA);
            if (a >= 0){
                out.println("A must be a interger number");
                return;
            }
           
        } catch (Exception e) {
            out.println("A must be a integer number ! ");
            return;
        }
        
        
        
        
        try {
            b = Integer.parseInt(txtB);
            if (b >= 0){
                out.println("B must be a interger number");
                return;
            }
           
        } catch (Exception e) {
            out.println("B must be a integer number ! ");
            return;
        }
        int result = GCD(a,b);
        out.println("GCD+("+a+","+b+")+"+result);
            
        
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
