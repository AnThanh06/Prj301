
package controller;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "CalculatorController", urlPatterns = {"/Cal"})
public class CalculatorController extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalculatorController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CalculatorController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            double a = Double.parseDouble(request.getParameter("a"));
            double b = Double.parseDouble(request.getParameter("b"));
            double c = Double.parseDouble(request.getParameter("c"));
            Result res = tinhPhuongTrinhBac2(a, b, c);
            String msg = converMessage(res.getFlag());
            dataRespone<Result> resp = new dataRespone(res, msg);
            request.setAttribute("data", resp);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String converMessage(int msgCode) {
        String msg = "";
        switch (msgCode) {
            case 1:
                msg = "Phương trình có nghiệm kép";
                break;
            case 2:
                msg = "Phương trình có hai nghiệm phân biệt";
                break;
            case 3:
                msg = "Phương trình có một nghiệm";
                break;
            case 0:
                msg = "Phường trình vô số nghiệm";
                break;
            default:
                msg = "Phương trình vô nghiệm";
        }
        return msg;
    }

    public Result tinhPhuongTrinhBac2(double a, double b, double c) {
        Result rs = new Result(0, 0, 0);
        // Kiểm tra xem a có bằng 0 không (nếu a = 0 thì không phải phương trình bậc 2)
        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    rs.setFlag(99);
                } else {
                    rs.setFlag(0);
                }
            } else {
                double x = -c / b;
                rs.setFlag(3);
                rs.setX1(x);
                System.out.println(": x = " + x);
            }
        } else {

            // Tính delta
            double delta = b * b - 4 * a * c;
            if (delta < 0) {
                rs.setFlag(0);
                System.out.println("Phường trình vô nghiệm");
            }
            // Xử lý các trường hợp của delta
            if (delta > 0) {
                // Hai nghiệm phân biệt
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                rs.setFlag(2);
                rs.setX1(x1);
                rs.setX2(x2);
            } else if (delta == 0) {
                // Nghiệm kép
                double x = -b / (2 * a);
                rs.setFlag(1);
                rs.setX1(x);
            }
        }
        return rs;
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