/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itunes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nunoandrebarbosagomes
 */
public class DBConnector {

    private final static String DATABASE_ERROR = "Cannot connect the database!";

    //default parameters
    private final static String DEFAULT_URL = "jdbc:mysql://localhost:3306/mydb";
    private final static String DEFAULT_USERNAME = "root";
    private final static String DEFAULT_PASSWORD = "";

    private Connection connection = null;

    public DBConnector() {
        this.setConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    public DBConnector(String url, String username, String password) {
        this.setConnection(url, username, password);
    }

    public final void setConnection(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(DATABASE_ERROR);
        } 
    }

    public boolean isConnected() {
        return connection != null;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignore) {
            }
        }
    }

}
