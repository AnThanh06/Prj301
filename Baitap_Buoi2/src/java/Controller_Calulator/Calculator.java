/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller_Calulator;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thanh
 */
@WebServlet(name = "Calculator", urlPatterns = {"/Calculator"})
public class Calculator extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Calculator</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Calculator at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        Double a = Double.parseDouble(request.getParameter("txtNumber1"));
        Double b = Double.parseDouble(request.getParameter("txtNumber2"));
        Double c = Double.parseDouble(request.getParameter("txtNumber3"));
        
        
                    if (a == 0) {
                if (b == 0) {
                    if (c == 0) {
                        out.println("<div class='result'>Phương trình vô số nghiệm</div>");
                    } else {
                        out.println("<div class='result'>Phương trình vô nghiệm</div>");
                    }
                } else {
                    // Phương trình bậc 1: bx + c = 0
                    double x = -c / b;
                    out.println("<div class='result'>Phương trình có một nghiệm: x = " + String.format("%.2f", x) + "</div>");
                }
            } else {
                // Tính delta
                double delta = b * b - 4 * a * c;
                
                if (delta > 0) {
                    double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                    double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                    out.println("<div class='result'>");
                    out.println("Phương trình có 2 nghiệm phân biệt:<br>");
                    out.println("x1 = " + String.format("%.2f", x1) + "<br>");
                    out.println("x2 = " + String.format("%.2f", x2));
                    out.println("</div>");
                } else if (delta == 0) {
                    double x = -b / (2 * a);
                    out.println("<div class='result'>");
                    out.println("Phương trình có nghiệm kép:<br>");
                    out.println("x1 = x2 = " + String.format("%.2f", x));
                    out.println("</div>");
                } else {
                    double realPart = -b / (2 * a);
                    double imaginaryPart = Math.sqrt(-delta) / (2 * a);
                    out.println("<div class='result'>");
                    out.println("Phương trình có 2 nghiệm phức:<br>");
                    out.println("x1 = " + String.format("%.2f + %.2fi", realPart, imaginaryPart) + "<br>");
                    out.println("x2 = " + String.format("%.2f - %.2fi", realPart, imaginaryPart));
                    out.println("</div>");
                }
            }

        
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
