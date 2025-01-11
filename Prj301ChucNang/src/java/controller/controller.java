
package controller;

import com.sun.glass.events.WindowEvent;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import dto.Result;
import dto.response;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "controller", urlPatterns = {"/controller"})
public class controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String a_raw = request.getParameter("a");
        String b_raw = request.getParameter("b");
        String c_raw = request.getParameter("c");
        
        try {
            double a = Double.parseDouble(a_raw);
            double b = Double.parseDouble(b_raw);
            double c = Double.parseDouble(c_raw);
            
            Result res = GiaiPhuongtrinh(a, b, c);
            String msg = convertmessage(res.getFlag());
            response<Result> resp = new response<>(res, msg);
            
            request.setAttribute("data", resp);
            request.setAttribute("a", a);
            request.setAttribute("b", b);
            request.setAttribute("c", c);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Vui lòng nhập số hợp lệ");
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public String convertmessage(int msgCode) {
        String msg = "";
        switch(msgCode) {
            case 1:
                msg = "Phương trình có nghiệm kép";
                break;
            case 2:
                msg = "Phương trình có 2 nghiệm phân biệt";
                break;
            case 3:
                msg = "Phương trình có 1 nghiệm";
                break;
            case 4:
                msg = "Phương trình vô nghiệm";
                break;
            default:
                msg = "Phương trình vô số nghiệm";
                break;
        }
        return msg;
    }

    public Result GiaiPhuongtrinh(double a, double b, double c) {
        Result res = new Result(0, 0, 0);
        
        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    res.setFlag(99); // vô số nghiệm
                } else {
                    res.setFlag(4); // vô nghiệm
                }
            } else {
                double x = -c/b;
                res.setFlag(3); // một nghiệm
                res.setX1(x);
            }
        } else {
            double delta = b*b - 4*a*c;
            if (delta < 0) {
                res.setFlag(4); // vô nghiệm
            } else if (delta == 0) {
                double x = -b/(2*a);
                res.setFlag(1); // nghiệm kép
                res.setX1(x);
                res.setX2(x);
            } else {
                double x1 = (-b + Math.sqrt(delta))/(2*a);
                double x2 = (-b - Math.sqrt(delta))/(2*a);
                res.setFlag(2); // hai nghiệm phân biệt
                res.setX1(x1);
                res.setX2(x2);
            }
        }
        return res;
    }
}
