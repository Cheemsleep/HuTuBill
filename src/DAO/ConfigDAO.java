package DAO;


import Entity.Config;
import Util.DBUtil;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigDAO {
    //DAO层是用于实例与数据库表进行ORM映射，即将Config实例转换为Config表中的记录，又反过来将记录转换为Config实例

    /**
     *
     * execute() 执行此 PreparedStatement对象中的SQL语句，这可能是任何类型的SQL语句。
     * executeQuery() 执行此 PreparedStatement对象中的SQL查询，并返回查询 PreparedStatement的 ResultSet对象。
     * executeUpdate() 执行在该SQL语句PreparedStatement对象，它必须是一个SQL数据操纵语言（DML）语句，如INSERT ， UPDATE或DELETE ; 或不返回任何内容的SQL语句，例如DDL语句
     * 统计数据
     * @return
     */
    public int getTotal() {
        int total = 0;
        try(Connection con = DBUtil.getConnection(); Statement statement = con.createStatement();){
            String sql = "SELECT count(*) FROM config"; //准备sql语句获取所有行
            ResultSet rs = statement.executeQuery(sql);//执行查询
            while (rs.next()){
                total = rs.getInt(1);
            }
            System.out.println("total: "+total);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return total;
    }

    /**
     * 将实体类config转换为记录添加到表中
     * @param config
     */
    public void add(Config config) {
        String sql = "INSERT INTO config VALUES(null,?,?)";
        try(Connection con = DBUtil.getConnection(); PreparedStatement pre = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
            pre.setString(1,config.getKey());
            pre.setString(2,config.getValue());//设置prepareStatement中sql语句变量
            pre.execute();
            ResultSet rs = pre.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("id = " + id);
                config.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Config config) {
        String sql = "UPDATE config SET KEY_= ?, value=? WHERE id = ?";
        try(Connection con = DBUtil.getConnection();PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1,config.getKey());
            pre.setString(2,config.getValue());
            pre.setInt(3,config.getId());
            pre.execute();
            System.out.println("update");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try(Connection con = DBUtil.getConnection();Statement statement = con.createStatement()){
            String sql = "DELETE FROM config WHERE id = " + id;
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Config get(int id) {
        Config config = null;

        try(Connection con = DBUtil.getConnection(); Statement statement = con.createStatement()){
            String sql = "SELECT * FROM config WHERE id = " + id;
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                config = new Config();
                String key = rs.getString("key_");
                String value = rs.getString("value");
                config.setKey(key);
                config.setValue(value);
                config.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return config;
    }

    /**
     * 查询所有数据
     * @return
     */
    public List<Config> list() {
        return list(0, Short.MAX_VALUE);
    }

    /**
     * 分页查询
     * @param start
     * @param count
     * @return
     */
    public List<Config> list(int start, int count) {
        List<Config> configs = new ArrayList<>();
        String sql = "SELECT * FROM config ORDER BY id DESC limit ?,?";
        try(Connection con = DBUtil.getConnection();PreparedStatement pre = con.prepareStatement(sql)){
            pre.setInt(1,start);
            pre.setInt(2,count);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Config config = new Config();
                int id = rs.getInt(1);
                String key = rs.getString("key_");
                String value = rs.getString("value");
                config.setId(id);
                config.setKey(key);
                config.setValue(value);
                configs.add(config);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return configs;
    }

    public Config getByKey(String key) {
        Config config = null;
        String sql = "SELECT * FROM config WHERE key_ = ?";
        try(Connection con = DBUtil.getConnection();PreparedStatement pre = con.prepareStatement(sql)){
            pre.setString(1,key);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                config = new Config();
                int id = rs.getInt("id");
                String value = rs.getString("value");
                config.setValue(value);
                config.setId(id);
                config.setKey(key);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static void main(String[] args) {

    }
}
