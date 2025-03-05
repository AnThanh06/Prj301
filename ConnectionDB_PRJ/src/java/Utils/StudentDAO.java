/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import entity.Student;
import java.util.ArrayList;
import java.sql.*;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author thanh
 */
public class StudentDAO extends DBUtils{
    public List<Student>getALLStudent(){
        String sql = "Select * from Student";
        List<Student> List = new ArrayList<>();
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                Student s = new Student(rs.getInt("id"), rs.getInt("age"), rs.getString("name"));
                List.add(s);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List;
    }
        
        
        
        
        
          public boolean removeStudent(int id) {
        String sql = "DELETE FROM Student WHERE id = ?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM Student WHERE id = ?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                return new Student(rs.getInt("id"), rs.getInt("age"), rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void addStudent(int id, String name, int age) {
        String sql = "INSERT INTO Student (id, name, age) VALUES (?, ?, ?)";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setString(2, name);
            stm.setInt(3, age);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
        
        
        

    public static void main(String[] args) {
        StudentDAO  std = new StudentDAO();
        List<Student> List = std.getALLStudent();
        for (Student student : List) {
            System.out.println(student.toString());
            
        }
    }
    
    
}
