package jm.task.core.jdbc.util;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Util {
    public static final String URL = "jdbc:mysql://localhost:3306/new_schema?useSSL=false&serverTimezone=UTC";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";


    public static Connection getConnectionJDBC() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
