package lib;

import java.sql.*;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Database {
    Connection conn;

    public Database() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://10.18.200.70:3307";
            String username = "irena.vn";
            String password = "123456";
            conn = DriverManager.getConnection(url, username, password);
            log.info("connected");

        } catch (SQLException ex) {
            log.info("SQLException: " + ex.getMessage());
            log.info("SQLState: " + ex.getSQLState());
            log.info("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getData(String sql) throws SQLException {
        String data = null;
        try {
            log.info(sql);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                data = resultSet.getString(1);
            }
            log.info("data_sql: " + data);
            return data;
        } catch (SQLException ex) {
            return null;
        }
    }

    public ResultSet exeQuery(String sql) {
        try {
            log.info(sql);
            Statement statement = conn.createStatement();
            return statement.executeQuery(sql);
        } catch (Exception ex) {
            return null;
        }
    }
}