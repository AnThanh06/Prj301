package controller;

import dao.UserDAO;

import dao.ProjectDAO;
import dto.ProjectDTO;
import dto.UserDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})

public class MainController extends HttpServlet {

    private static final String Login_Page = "Login.jsp";

    public UserDTO getUser(String strUsername) {
        UserDAO udao = new UserDAO();
        UserDTO user = udao.readById(strUsername);
        return user;

    }

    public boolean isValidLogin(String strUsername, String strPassword) {
        UserDTO user = getUser(strUsername);
        if (user == null) {
            return false;
        }

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
            System.out.println("action: " + action);
            if (action == null) {
                url = Login_Page;
            } else {
                if (action.equals("Login")) {
                    String strUsername = request.getParameter("txtUsername");
                    String strPassword = request.getParameter("txtPassword");
                    if (isValidLogin(strUsername, strPassword)) {
                        url = "search.jsp";
                        UserDTO user = getUser(strUsername);
                        request.setAttribute("user", user);
                    } else {
                        request.setAttribute("message", "Incorrect UserID or Password");
                        url = "Login.jsp";
                    }
                } else if (action.equals("search")) {
                    ProjectDAO pDAO = new ProjectDAO();
                    String searchTerm = request.getParameter("searchTerm");
                    List<ProjectDTO> Projects = pDAO.searchByTitle(searchTerm);
                    request.setAttribute("Projects", Projects);
                    url = "search.jsp";

                } else if (action.equals("search") || action.equals("viewAll")) {
                    ProjectDAO pDAO = new ProjectDAO();
                    String searchTerm = request.getParameter("searchTerm");

                    List<ProjectDTO> projects;
                    if (searchTerm == null || searchTerm.trim().isEmpty()) {
                        projects = pDAO.getAllProjects(); // Lấy toàn bộ dự án
                    } else {
                        projects = pDAO.searchByTitle(searchTerm); // Tìm kiếm theo từ khóa
                    }

                    request.setAttribute("Projects", projects);
                    url = "search.jsp";
                } else if (action.equals("updateProject")) {
                    int projectId = Integer.parseInt(request.getParameter("projectId"));
                    String projectName = request.getParameter("projectName");
                    String description = request.getParameter("description");
                    String status = request.getParameter("status");
                    Date estimatedLaunch = Date.valueOf(request.getParameter("estimatedLaunch"));

                    ProjectDTO updatedProject = new ProjectDTO(projectId, projectName, description, status, estimatedLaunch);
                    ProjectDAO pDAO = new ProjectDAO();

                    boolean success = pDAO.update(updatedProject);

                    if (success) {
                        request.setAttribute("message", "Project updated successfully!");
                    } else {
                        request.setAttribute("message", "Failed to update project.");
                    }

                    request.getRequestDispatcher("search.jsp").forward(request, response);
                }

            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());

        } finally {
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
