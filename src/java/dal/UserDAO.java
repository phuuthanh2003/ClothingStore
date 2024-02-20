/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.UserDTO;

/**
 *
 * @author HuuThanh
 */
public class UserDAO extends DBContext {
    private static final String LOGIN = "SELECT * FROM Users WHERE username=? AND password=? and status=1";
    private static final String GETDATA = "SELECT * FROM Users Where status = 1";
    
    
    public List<UserDTO> getData() throws SQLException {
        List<UserDTO> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETDATA);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String email = rs.getString("email");
                    String avatar = rs.getString("avatar");
                    String userName = rs.getString("userName");
                    String password = rs.getString("password");
                    int roleId = rs.getInt("roleId");
                    boolean status = rs.getBoolean("status");
                    users.add(new UserDTO(id, firstName, lastName, email, avatar, userName, password, roleId, status));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return users;
    }
    
    public UserDTO checkLogin(String userName, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, userName);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    String email = rs.getString("email");
                    String avatar = rs.getString("avatar");
                    int roleid = rs.getInt("roleID");
                    boolean roleID = rs.getBoolean("roleID");
                    user = new UserDTO(id, firstname, lastname, email, avatar, userName, password, roleid, roleID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }
    
    public static void main(String[] args) throws SQLException {
        UserDAO dao = new UserDAO();
        UserDTO user = dao.checkLogin("user1", "12345");
        List<UserDTO> list = dao.getData();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getAvatar());
        }
        System.out.println(user.getAvatar());
    }
}
