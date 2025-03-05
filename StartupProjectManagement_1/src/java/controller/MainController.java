package controller;

import dao.UserDAO;
import dao.ProjectDAO;
import dto.ProjectDTO;
import dto.UserDTO;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "Login.jsp";
    private static final String SEARCH_PAGE = "search.jsp";
    private static final String UPDATE_PAGE = "updateProject.jsp";

    private UserDTO getUser(String username) {
        UserDAO udao = new UserDAO();
        return udao.readById(username);
    }

    private boolean isValidLogin(String username, String password) {
        UserDTO user = getUser(username);
        return user != null && user.getPassword() != null && user.getPassword().equals(password);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        String action = request.getParameter("action");

        try {
            if (action == null) {
                url = LOGIN_PAGE;
            } else {
                switch (action) {
                    case "Login":
                        handleLogin(request, response);
                        return;
                    case "search":
                    case "viewAll":
                        handleSearch(request, response);
                        return;
                    case "edit":
                        handleEdit(request, response);
                        return;
                    case "updateProject":
                        handleUpdate(request, response);
                        return;
                    case "logout":
                        handleLogout(request, response);
                        return;
                    case "create":
                        request.getRequestDispatcher("createProject.jsp").forward(request, response);
                        return;
                    case "createProject":
                        handleCreate(request, response);
                        return;
                    default:
                        url = LOGIN_PAGE;
                        break;
                }
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
            request.getSession().setAttribute("error", "An error occurred: " + e.getMessage());
        } finally {
            if (!response.isCommitted()) {
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        HttpSession session = request.getSession();

        if (isValidLogin(username, password)) {
            session.setAttribute("user", getUser(username));
            response.sendRedirect("MainController?action=viewAll");
        } else {
            request.setAttribute("message", "Incorrect UserID or Password");
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }

    private void handleSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProjectDAO pDAO = new ProjectDAO();
        String searchTerm = request.getParameter("searchTerm");
        List<ProjectDTO> projects;

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            projects = pDAO.getAllProjects();
        } else {
            projects = pDAO.searchByTitle(searchTerm);
        }

        request.setAttribute("Projects", projects);
        request.getRequestDispatcher(SEARCH_PAGE).forward(request, response);
    }

    private void handleEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int projectId = Integer.parseInt(request.getParameter("projectId"));
            ProjectDTO project = (ProjectDTO) new ProjectDAO().readById(projectId);

            if (project != null) {
                request.setAttribute("project", project);
                request.getRequestDispatcher(UPDATE_PAGE).forward(request, response);
            } else {
                request.getSession().setAttribute("error", "Project not found");
                response.sendRedirect("MainController?action=viewAll");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "Invalid project ID");
            response.sendRedirect("MainController?action=viewAll");
        }
    }
    // Trong MainController.java

    private void handleCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        try {
            String projectName = request.getParameter("projectName");
            String description = request.getParameter("description");
            String status = request.getParameter("status");
            Date estimatedLaunch = Date.valueOf(request.getParameter("estimatedLaunch"));

            ProjectDTO newProject = new ProjectDTO(
                    0, // ID sẽ tự động tăng
                    projectName,
                    description,
                    status,
                    estimatedLaunch
            );

            boolean success = new ProjectDAO().create(newProject);

            if (success) {
                session.setAttribute("message", "Project created successfully!");
            } else {
                session.setAttribute("error", "Failed to create project");
            }
        } catch (IllegalArgumentException e) {
            session.setAttribute("error", "Invalid date format. Use yyyy-MM-dd");
        } catch (Exception e) {
            session.setAttribute("error", "Error creating project: " + e.getMessage());
        }

        response.sendRedirect("MainController?action=viewAll");
    }

// Thêm case vào processRequest
    private void handleUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();
        try {
            int projectId = Integer.parseInt(request.getParameter("projectId"));
            String projectName = request.getParameter("projectName");
            String description = request.getParameter("description");
            String status = request.getParameter("status");
            Date estimatedLaunch = Date.valueOf(request.getParameter("estimatedLaunch"));

            ProjectDTO updatedProject = new ProjectDTO(
                    projectId,
                    projectName,
                    description,
                    status,
                    estimatedLaunch
            );

            boolean success = new ProjectDAO().update(updatedProject);

            if (success) {
                session.setAttribute("message", "Project updated successfully!");
            } else {
                session.setAttribute("error", "Failed to update project");
            }
        } catch (IllegalArgumentException e) {
            session.setAttribute("error", "Invalid date format");
        } catch (Exception e) {
            session.setAttribute("error", "Error updating project: " + e.getMessage());
        }

        response.sendRedirect("MainController?action=viewAll");
    }

    private void handleLogout(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(LOGIN_PAGE);
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
}
