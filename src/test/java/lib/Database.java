package lib;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cts.ui.model.Category;
import cts.ui.model.EnvType;
import cts.ui.model.database.DBType;
import cts.ui.steps.ParameterDefinitions;
import lombok.extern.slf4j.Slf4j;

import static cts.ui.model.Category.SHORT_WAITING;

@Slf4j
public class Database {
    private Connection conn;
    private String url;
    private String username;
    private String password;
    private String classForName;

    public Database(DBType dbType) {
        GetProperties prop = new GetProperties();
        if (dbType == DBType.CTS_DataCenter) {
            this.classForName = "com.mysql.cj.jdbc.Driver";
            switch (ParameterDefinitions.envType) {
                case Local1, Local2, Local3, LocalMain:
                    this.url = prop.getProperty("local.master.url");
                    this.username = prop.getProperty("local.master.username");
                    this.password = prop.getProperty("local.master.password");
                    break;

                case UAT:
                    this.url = prop.getProperty("uat.master.url");
                    this.username = prop.getProperty("uat.master.username");
                    this.password = prop.getProperty("uat.master.password");
                    break;
                case Prod:
                    this.url = prop.getProperty("prod.master.url");
                    this.username = prop.getProperty("prod.master.username");
                    this.password = prop.getProperty("prod.master.password");
                    break;
                default:
                    break;
            }
        } else if (dbType == DBType.CustDB) {
            this.classForName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            switch (ParameterDefinitions.envType) {
                case Local1, Local2, Local3, LocalMain:
//                    this.url = "jdbc:sqlserver://10.18.200.233;servername=DB233;databaseName=CUSTDB;integratedSecurity=true;sslProtocol=TLSv1";
//                    this.username = "testteam";
//                    this.password = "@quality#";
                    break;
                case UAT:
                    break;
                case Prod:
                    break;
            }
        }
        connect();
    }

    public void connect() {
        try {
            Class.forName(this.classForName);
            conn = DriverManager.getConnection(this.url, this.username, this.password);
            log.info("connected");

        } catch (SQLException ex) {
            log.info("SQLException: " + ex.getMessage());
            log.info("SQLState: " + ex.getSQLState());
            log.info("getErrorCode: " + ex.getErrorCode());
            log.info("getCause: " + ex.getCause());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//        public String getData (String sql) throws SQLException {
//            String data = null;
//            try {
//                log.info(sql);
//                Statement statement = conn.createStatement();
//                ResultSet resultSet = statement.executeQuery(sql);
//                while (resultSet.next()) {
//                    data = resultSet.getString(1);
//                }
//                log.info("data_sql: " + data);
//                return data;
//            } catch (SQLException ex) {
//                return null;
//            }
//        }

    public ArrayList exeQuery(String sql) throws SQLException {
        try {
            log.info(sql);
            Statement statement = conn.createStatement();
            statement.setQueryTimeout((int) Category.SHORT_WAITING / 1000);
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData md = resultSet.getMetaData();
            int columns = md.getColumnCount();
            ArrayList list = new ArrayList(columns);
            while (resultSet.next()) {
                HashMap row = new HashMap(columns);
                for (int i = 1; i <= columns; ++i) {
                    row.put(md.getColumnLabel(i), resultSet.getObject(i));
                }
                list.add(row);
            }
            return list;
        } catch (Exception ex) {
            return null;
        } finally {
            conn.close();
            log.info("closed");
        }
    }
}