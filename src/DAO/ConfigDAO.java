package DAO;


import Entity.Config;
import Util.DBUtil;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigDAO {
    //DAO��������ʵ�������ݿ�����ORMӳ�䣬����Configʵ��ת��ΪConfig���еļ�¼���ַ���������¼ת��ΪConfigʵ��

    /**
     *
     * execute() ִ�д� PreparedStatement�����е�SQL��䣬��������κ����͵�SQL��䡣
     * executeQuery() ִ�д� PreparedStatement�����е�SQL��ѯ�������ز�ѯ PreparedStatement�� ResultSet����
     * executeUpdate() ִ���ڸ�SQL���PreparedStatement������������һ��SQL���ݲ������ԣ�DML����䣬��INSERT �� UPDATE��DELETE ; �򲻷����κ����ݵ�SQL��䣬����DDL���
     * ͳ������
     * @return
     */
    public int getTotal() {
        int total = 0;
        try(Connection con = DBUtil.getConnection(); Statement statement = con.createStatement();){
            String sql = "SELECT count(*) FROM config"; //׼��sql����ȡ������
            ResultSet rs = statement.executeQuery(sql);//ִ�в�ѯ
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
     * ��ʵ����configת��Ϊ��¼��ӵ�����
     * @param config
     */
    public void add(Config config) {
        String sql = "INSERT INTO config VALUES(null,?,?)";
        try(Connection con = DBUtil.getConnection(); PreparedStatement pre = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
            pre.setString(1,config.getKey());
            pre.setString(2,config.getValue());//����prepareStatement��sql������
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
     * ��ѯ��������
     * @return
     */
    public List<Config> list() {
        return list(0, Short.MAX_VALUE);
    }

    /**
     * ��ҳ��ѯ
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
