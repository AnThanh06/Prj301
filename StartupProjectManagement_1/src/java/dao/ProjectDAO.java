
package dao;

import java.time.LocalDate;
import dto.ProjectDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author thanh
 */
public class ProjectDAO implements IDAO<Object, Object>{

    

    @Override
    public List<Object> readAll() {
        return null;
    }

    @Override
    public Object readById(Object id) {
        return null;
    }

    

    
    @Override
public boolean update(Object entity) {
    if (!(entity instanceof ProjectDTO)) {
        return false;
    }
    
    ProjectDTO project = (ProjectDTO) entity;
    String sql = "UPDATE tblStartupProjects SET "
               + "project_name = ?, description = ?, status = ?, estimated_launch = ? "
               + "WHERE project_id = ?";
    
    try (Connection conn = DBUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        // Debug: In giá trị các tham số
        System.out.println("[DEBUG] Update parameters:");
        System.out.println("1. Project Name: " + project.getProjectName());
        System.out.println("2. Description: " + project.getDescription());
        System.out.println("3. Status: " + project.getStatus());
        System.out.println("4. Estimated Launch: " + project.getEstimatedLaunch());
        System.out.println("5. Project ID: " + project.getId());
        
        ps.setString(1, project.getProjectName());
        ps.setString(2, project.getDescription());
        ps.setString(3, project.getStatus());
        ps.setDate(4, project.getEstimatedLaunch());
        ps.setInt(5, project.getId());
        
        int rowsUpdated = ps.executeUpdate();
        System.out.println("[DEBUG] Rows affected: " + rowsUpdated); // Thêm dòng này
        
        return rowsUpdated > 0;
        
    } catch (Exception e) {
        System.out.println("[ERROR] Update failed:");
        e.printStackTrace();
        return false;
    }
}



    @Override
    public List<Object> search(String searchTerm) {
        return null;
    }
    
    public List<ProjectDTO> searchByTitle(String searchTerm) {
    String sql = "SELECT * FROM tblStartupProjects WHERE project_name LIKE ?";
    List<ProjectDTO> list = new ArrayList<>();
    
    try (Connection conn = DBUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setString(1, "%" + searchTerm + "%");
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            ProjectDTO project = new ProjectDTO(
                rs.getInt("project_id"),
                rs.getString("project_name"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getDate("estimated_launch")

            );
            list.add(project);
        }
    } catch (Exception e) {
        System.out.println(e.toString());
    }
    
    return list;
}
    public List<ProjectDTO> getAllProjects() {
    String sql = "SELECT * FROM tblStartupProjects";
    List<ProjectDTO> list = new ArrayList<>();
    
    try (Connection conn = DBUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        while (rs.next()) {
            ProjectDTO project = new ProjectDTO(
                rs.getInt("project_id"),
                rs.getString("project_name"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getDate("estimated_launch")
            );
            list.add(project);
        }
    } catch (Exception e) {
        System.out.println(e.toString());
    }
    
    return list;
}

    @Override
    public boolean delete(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProjectDTO readById(int projectId) {
    String sql = "SELECT * FROM tblStartupProjects WHERE project_id = ?";
    try (Connection conn = DBUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, projectId);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            return new ProjectDTO(
                rs.getInt("project_id"),
                rs.getString("project_name"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getDate("estimated_launch")
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
    // Trong ProjectDAO.java
@Override
public boolean create(Object entity) {
    if (!(entity instanceof ProjectDTO)) {
        return false;
    }
    
    ProjectDTO project = (ProjectDTO) entity;
    String sql = "INSERT INTO tblStartupProjects "
               + "(project_name, description, status, estimated_launch) "
               + "VALUES (?, ?, ?, ?)";
    
    try (Connection conn = DBUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setString(1, project.getProjectName());
        ps.setString(2, project.getDescription());
        ps.setString(3, project.getStatus());
        ps.setDate(4, project.getEstimatedLaunch());
        
        int rowsInserted = ps.executeUpdate();
        return rowsInserted > 0;
        
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
    
    


    

}
