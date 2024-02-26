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
import model.CategoryDTO;

/**
 *
 * @author HuuThanh
 */
public class CategoryDAO extends DBContext {

    private static final String GETDATA = "SELECT * FROM Categories";
    private static final String GET_CATEGORY_BYID = "SELECT * FROM Categories WHERE categoryid = ?";
    private static final String INSERT_CATEGORY = "INSERT INTO Categories VALUES (?);";

    public List<CategoryDTO> getData() throws SQLException {
        List<CategoryDTO> categories = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETDATA);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int categoryId = rs.getInt("categoryid");
                    String categoryName = rs.getString("categoryname");
                    categories.add(new CategoryDTO(categoryId, categoryName));
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
        return categories;
    }
    
    public CategoryDTO getCategoryById(int id) throws SQLException {
        CategoryDTO category = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_CATEGORY_BYID);
                ptm.setInt(1, id);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int categoryId = rs.getInt("categoryid");
                    String categoryName = rs.getString("categoryname");
                    category = new CategoryDTO(categoryId, categoryName);
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
        return category;
    }
    
    public boolean insertCategory(String categoryName) {
        Connection con = null;
        PreparedStatement ptm = null;
        try {
            con = getConnection();
            if(con != null) {
                ptm = con.prepareStatement(INSERT_CATEGORY);
                ptm.setString(1, categoryName);
                ptm.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static void main(String[] args) throws SQLException {
        CategoryDAO dao = new CategoryDAO();
        List<CategoryDTO> list = dao.getData();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getCategoryName());
        }
        
        if(dao.insertCategory("ao da")) {
            System.out.println("OK");
        }else {
            System.out.println("FAIL");
        }
    }
}
