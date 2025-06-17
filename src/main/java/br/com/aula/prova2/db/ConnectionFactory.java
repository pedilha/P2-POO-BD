package br.com.aula.prova2.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {

    private static final String URL = "jdbc:postgresql://localhost:5432/Prova2";
    private static final String USER = "postgres";
    private static final String PASSWORD = "130303";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}