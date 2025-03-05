package dao;

import dto.UserDTO;
import utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDAO implements IDAO<UserDTO, String> {

    // Sửa toàn bộ SQL dùng đúng tên cột
    @Override
    public boolean create(UserDTO entity) {
        String sql = "INSERT INTO tblUsers (Username, Name, Role, Password) VALUES (?, ?, ?, ?)"; // Sửa tên cột
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getRole());
            ps.setString(4, entity.getPassword());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<UserDTO> readAll() {
        List<UserDTO> list = new ArrayList<>();
        String sql = "SELECT Username, Name, Password, Role FROM tblUsers"; // Sửa tên cột
        try (Connection conn = DBUtils.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                UserDTO user = new UserDTO(
                        rs.getString("Username"),
                        rs.getString("Name"),
                        rs.getString("Password"),
                        rs.getString("Role")
                );
                list.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public UserDTO readById(String id) {
        String sql = "SELECT Username, Name, Password, Role FROM tblUsers WHERE Username = ?"; // Sửa tên cột
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new UserDTO(
                        rs.getString("Username"),
                        rs.getString("Name"),
                        rs.getString("Password"),
                        rs.getString("Role")
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(UserDTO entity) {
        String sql = "UPDATE tblUsers SET Name = ?, Role = ?, Password = ? WHERE Username = ?"; // Sửa tên cột
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getRole());
            ps.setString(3, entity.getPassword());
            ps.setString(4, entity.getUsername());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
//// ở user e không có ID nên e để cho có :)))
    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM tblUsers WHERE Username = ?"; // Sửa tên cột
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<UserDTO> search(String searchTerm) {
        List<UserDTO> list = new ArrayList<>();
        String sql = "SELECT Username, Name, Role, Password FROM tblUsers "
                + "WHERE Username LIKE ? OR Name LIKE ? OR Role LIKE ?"; // Sửa tên cột
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String searchPattern = "%" + searchTerm + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserDTO user = new UserDTO(
                        rs.getString("Username"),
                        rs.getString("Name"),
                        rs.getString("Password"),
                        rs.getString("Role")
                );
                list.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}