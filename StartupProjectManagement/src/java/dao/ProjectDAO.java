
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
    public boolean create(Object entity) {
        return false;
    }

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
        return false;
    }

    @Override
    public boolean delete(Object id) {
        return false;
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

    


    

}
