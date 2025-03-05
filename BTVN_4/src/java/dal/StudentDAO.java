/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.*;
import entity.Student;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thitr
 */
public class StudentDAO extends DBContext {
//StudentID int primary key,
//FirstName nvarchar(50),
//LastName nvarchar(50),
//DateOfBirth date,
//Gender nvarchar(10)

    public List<Student> getAllStudent() {
        String sql = "select * from student";
        List<Student> list = new ArrayList<>();
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                Student s = new Student(res.getInt("StudentID"), res.getString("Gender"), res.getString("FirstName"), res.getString("LastName"), res.getString("DateOfBirth"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Student getStudentById(int id) {
        String sql = "select * from Student where StudentID = ?";
        Student s = null;
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet res = stm.executeQuery();
            if (res.next()) {
                s = new Student(res.getInt("StudentID"), res.getString("FirstName"), res.getString("LastName"), res.getString("DateOfBirth"), res.getString("Gender"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public void createStudent(Student s) {

        if (getStudentById(s.getStudentID()) != null) {
            return;
        }
        String sql = "INSERT INTO [dbo].[Student]\n"
                + "           ([StudentID]\n"
                + "           ,[FirstName]\n"
                + "           ,[LastName]\n"
                + "           ,[DateOfBirth]\n"
                + "           ,[Gender])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, s.getStudentID());
            stm.setString(2, s.getFirstName());
            stm.setString(3, s.getLastName());
            stm.setString(4, s.getDob());
            stm.setString(5, s.getGender());
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteStudentById(int id) {
        if (getStudentById(id) != null) {
            return false;
        }
        String sql = "DELETE FROM [dbo].[Student]\n"
                + "         WHERE StudentID = ?";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void updateStudent(Student s) {
        if (getStudentById(s.getStudentID()) != null) {
            return;
        }
        String sql = "UPDATE [dbo].[Student]\n"
                + "   SET [FirstName] = ?\n"
                + "      ,[LastName] = ?\n"
                + "      ,[DateOfBirth] = ?\n"
                + "      ,[Gender] = ?\n"
                + " WHERE StudentID = ?";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(5, s.getStudentID());
            stm.setString(1, s.getFirstName());
            stm.setString(2, s.getLastName());
            stm.setString(3, s.getDob());
            stm.setString(4, s.getGender());
            stm.executeUpdate();
        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        StudentDAO std = new StudentDAO();
        List<Student> list = std.getAllStudent();
        for (Student student : list) {
            System.out.println(student.toString());
        }

    }
}
