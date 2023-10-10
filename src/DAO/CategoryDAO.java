package DAO;

import Entity.Category;
import Util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    /**
     * category类的Dao层,实现增删改查
     */
    public int getTotal() {
        int total = 0;
        try(Connection con = DBUtil.getConnection(); Statement statement = con.createStatement()) {
            String sql = "SELECT COUNT(*) FROM category";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
            System.out.println("total: " + total);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(Category category) {
        String sql = "INSERT INTO category VALUES(null,?)";
        try(Connection con = DBUtil.getConnection(); PreparedStatement pre = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            pre.setString(1,category.getName());
            pre.execute();
            /*
            报错： Generated keys not requested. You need to specify Statement.RETURN_GENERATED_KEYS to Statement.executeUpdate() or Connection.prepareStatement().
            需要在创建PrepareStatement时指定自增主键：
             */
            ResultSet rs = pre.getGeneratedKeys();
            while (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("id = " + id);
                category.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM category WHERE id = ?";
        try (Connection con = DBUtil.getConnection();PreparedStatement pre = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            pre.setInt(1,id);
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Category category) {
        String sql = "UPDATE category SET name = ? WHERE id = ?";
        try(Connection con = DBUtil.getConnection();PreparedStatement pre = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            pre.setString(1,category.getName());
            pre.setInt(2,category.getId());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category get(int id) {
        Category category = null;//防止没有查到而返回一个对象

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from category where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                category = new Category();
                String name = rs.getString(2);
                category.setName(name);
                category.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    /**
     * 分页查询
     */
    public List<Category> list() {
        return list(0,Short.MAX_VALUE);
    }

    public List<Category> list(int start,int count) {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category ORDER BY id DESC LIMIT ?,?";//mysql实现分页查询语句 从哪里开始-查询几个 desc-表详细信息
        try(Connection con = DBUtil.getConnection();PreparedStatement pre = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            pre.setInt(1,start);
            pre.setInt(2,count);
            ResultSet rs = pre.executeQuery();
            while(rs.next()) {
                Category category = new Category();
                int id = rs.getInt(1);
                String name = rs.getString(2);
                category.setId(id);
                category.setName(name);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    /**
     * 测试函数
     * @param args
     */
    public static void main(String[] args) {
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.getTotal();
    }
}
