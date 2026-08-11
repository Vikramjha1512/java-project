package com.login.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    // Database configuration - CHANGE THESE!
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/login_system";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = ""; // Change if you have password
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    
    // Private constructor
    private DBConnection() {
    }
    
    // Get database connection
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        
        try {
            // Load MySQL JDBC Driver
            Class.forName(JDBC_DRIVER);
            
            // Establish connection
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            conn.setAutoCommit(true);
            
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("SQL State: " + e.getSQLState());
            e.printStackTrace();
            throw e;
        }
        
        return conn;
    }
    
    // Close connection safely
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
    
    // Close PreparedStatement safely
    public static void closePreparedStatement(java.sql.PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement: " + e.getMessage());
            }
        }
    }
    
    // Close ResultSet safely
    public static void closeResultSet(java.sql.ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Error closing result set: " + e.getMessage());
            }
        }
    }
    
    // Test the database connection
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (Exception e) {
            System.err.println("Connection test failed: " + e.getMessage());
            return false;
        }
    }
}
