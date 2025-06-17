package br.com.aula.prova2.db;


import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            System.out.println("Conexão obtida com sucesso: " + conn);
        } catch (Exception e) {
            System.err.println("Falha ao conectar: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
