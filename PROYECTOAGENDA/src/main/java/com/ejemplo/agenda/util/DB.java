package com.ejemplo.agenda.util;

import java.sql.*;

public class DB {
    private static final String URL = "jdbc:mysql://localhost:3306/base_agenda";
    private static final String USER = "root";
    private static final String PASSWORD = "SCerizo12@";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL no encontrado", e);
        }
    }

    public static void cerrar(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}