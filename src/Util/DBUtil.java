package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    static String driverClassName = "com.mysql.jdbc.Driver";
    static String ip = "localhost";
    static int port = 3306;
    static String database = "hutubill";
    static String encoding = "GBK";
    static String loginName = "root";
    static String password = "admin";
    static {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s",ip,port,database,encoding);
        return DriverManager.getConnection(url,loginName,password);
    }

}
