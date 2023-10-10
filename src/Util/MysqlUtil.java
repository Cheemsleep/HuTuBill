package Util;

import java.io.*;

/**
 * 备份方法backup()
 * 通过Runtime调用mysqldump.exe进行备份
 *
 * 恢复方法recover
 * 通过Runtime调用mysql.exe进行数据的还原
 */

public class MysqlUtil {
    public static void backup(String mysqlPath, String backupFile) throws IOException {
        String commandFormat = "\"%s/bin/mysqldump.exe\" -u%s -p%s -hlocalhost -P%d %s -r \"%s\"";
        String command = String.format(commandFormat, mysqlPath, DBUtil.loginName, DBUtil.password, DBUtil.port, DBUtil.database, backupFile);
        Runtime.getRuntime().exec(command);
    }

    public static void recover(String mysqlPath, String recoverFile) {
        try {
            String commandFormat = "\"%s/bin/mysql.exe\" -u%s -p%s %s ";
            String command = String.format(commandFormat, mysqlPath, DBUtil.loginName, DBUtil.password, DBUtil.database);

            Process p = Runtime.getRuntime().exec(command);
            OutputStream out = p.getOutputStream();
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(recoverFile), "utf8"));
            while((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();

            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
            writer.write(outStr);
            writer.flush();
            out.close();
            br.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String mysqlPath = "D:/Program Files (x86)/mysql";
        //备份地址
        String file = "D:/Files/mysql_backup/hutubill.sql";

        backup(mysqlPath, file);

    }

}
