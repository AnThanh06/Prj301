/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dal.StudentDAO;
import entity.Student;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "StudentController", urlPatterns = {"/StudentController"})
public class StudentController extends HttpServlet {



 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentDAO std = new StudentDAO();
        List<Student> data = std.getAllStudent();
        request.setAttribute("data", data);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentDAO std = new StudentDAO();
        String msg = "";
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if(std.getStudentById(id)!=null){
                msg ="Student existed";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("createstd.jsp").forward(request, response);
            }else{
                std.createStudent(new Student(id, fname, lname, dob, gender));
                msg = "Add successfully";
                request.setAttribute("msg", msg);
                response.sendRedirect("StudentController");
            }
            
            
        } catch (Exception e) {
        }
    }


}