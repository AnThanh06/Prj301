/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import DAO.UserDAO;
import DTO.StudentDTO;

/**
 *
 * @author thanh
 */


/**
 *
 * @author tungi
 */
public class UserTest {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
//        UserDTO ud1 = new UserDTO("TLN01", "Le Nhat Tung", "AD", "khong_co_password");
//        userDAO.create(ud1);
        for (int i = 0; i < 10; i++) {
            StudentDTO ud_i = new StudentDTO("USER"+i, "Nguyen Van "+i, "US", "_"+i);
            userDAO.create(ud_i);
        }
    }
}
