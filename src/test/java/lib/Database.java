package lib;

import java.sql.*;
import java.util.List;

import cts.ui.model.EnvType;
import cts.ui.steps.ParameterDefinitions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Database {
    private Connection conn;
    private String url;
    private String username;
    private String password;

    public Database() {
        EnvType envType = ParameterDefinitions.getEnvironment();
        GetProperties prop = new GetProperties();
        switch (envType) {
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
        }
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
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