package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Entity.Record;
import Util.DBUtil;
import Util.DateUtil;

public class RecordDAO {

    public int getTotal() {
        int total = 0;
        try(Connection con = DBUtil.getConnection(); Statement statement = con.createStatement()) {
            String sql = "SELECT count(*) FROM record";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                total = rs.getInt(1);
            }
            System.out.println("total: " + total);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(Record record) {
        //id spend cid comment date
        String sql = "INSERT INTO record VALUE(null,?,?,?,?)";
        try(Connection con = DBUtil.getConnection(); PreparedStatement pre = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            pre.setInt(1,record.getSpend());
            pre.setInt(2,record.getCid());
            pre.setString(3,record.getComment());
            pre.setDate(4, DateUtil.util2sql(record.getDate()));
            pre.execute();
            ResultSet rs = pre.getGeneratedKeys();
            while(rs.next()) {
                int id = rs.getInt(1);
                record.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        System.out.println("delete");
        String sql = "DELETE FROM record WHERE id = "+id;
        try(Connection con = DBUtil.getConnection();Statement statement = con.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Record record) {
        String sql = "UPDATE record SET spend = ?, cid = ?, comment = ?, date = ? WHERE id = ?";
        try(Connection con = DBUtil.getConnection();PreparedStatement pre = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            pre.setInt(1,record.getSpend());
            pre.setInt(2,record.getCid());
            pre.setString(3,record.getComment());
            pre.setDate(4,DateUtil.util2sql(record.getDate()));
            pre.setInt(5,record.getId());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Record get(int id) {
        Record record = null;
        String sql = "SELECT * FROM record WHERE id = "+ id;
        try(Connection con = DBUtil.getConnection();Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                record = new Record();
                record.setId(id);
                record.setSpend(rs.getInt(2));
                record.setCid(rs.getInt(3));
                record.setComment(rs.getString(4));
                record.setDate(rs.getDate(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return record;
    }

    public List<Record> list() {
        return list(0,Short.MAX_VALUE);
    }
    public List<Record> list(int start,int count) {
        List<Record> records = new ArrayList<>();
        String sql = "SELECT * FROM record ORDER BY id LIMIT ?,?";
        try(Connection con = DBUtil.getConnection();PreparedStatement pre = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            pre.setInt(1,start);
            pre.setInt(2,count);
            ResultSet rs = pre.executeQuery();
            while(rs.next()) {
                Record record = new Record();
                record.setId(rs.getInt(1));
                record.setSpend(rs.getInt(2));
                record.setCid(rs.getInt(3));
                record.setComment(rs.getString(4));
                record.setDate(rs.getDate(5));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    public List<Record> list(int cid) {
        List<Record> records = new ArrayList<Record>();

        String sql = "select * from record where cid = ?";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, cid);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Record record = new Record();
                int id = rs.getInt("id");
                int spend = rs.getInt("spend");

                String comment = rs.getString("comment");
                java.util.Date date = rs.getDate("date");

                record.setSpend(spend);
                record.setCid(cid);
                record.setComment(comment);
                record.setDate(date);
                record.setId(id);
                records.add(record);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return records;
    }

    public List<Record> listToday(){
        return list(DateUtil.today());
    }

    public List<Record> list(Date day) {
        List<Record> records = new ArrayList<Record>();
        String sql = "select * from record where date =?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
            ps.setDate(1, DateUtil.util2sql(day));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Record record = new Record();
                record.setId(rs.getInt("id"));
                record.setCid(rs.getInt("cid"));
                record.setSpend(rs.getInt("spend"));

                record.setComment(rs.getString("comment"));
                record.setDate(rs.getDate("date"));

                records.add(record);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return records;
    }

    /**
     * 报错原因：导包出错，没有导入Util.Date包
     * @return
     */
    public List<Record> listThisMonth(){
        return list(DateUtil.monthBegin(),DateUtil.monthEnd());
    }
    public List<Record> list(Date start, Date end) {
        List<Record> records = new ArrayList<Record>();
        String sql = "select * from record where date >=? and date <= ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
            ps.setDate(1, DateUtil.util2sql(start));
            ps.setDate(2, DateUtil.util2sql(end));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Record record = new Record();
                int id = rs.getInt("id");
                int cid = rs.getInt("cid");
                int spend = rs.getInt("spend");

                String comment = rs.getString("comment");
                Date date = rs.getDate("date");

                record.setSpend(spend);
                record.setCid(cid);
                record.setComment(comment);
                record.setDate(date);
                record.setId(id);
                records.add(record);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return records;
    }
}