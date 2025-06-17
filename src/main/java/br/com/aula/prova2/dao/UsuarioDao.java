package br.com.aula.prova2.dao;


import br.com.aula.prova2.db.ConnectionFactory;
import br.com.aula.prova2.model.Usuario;

import java.sql.*;
import java.util.Optional;

public class UsuarioDao {

    public void insert(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (email, senha, cliente_id) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getSenha());
            ps.setInt(3, usuario.getClienteId());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) usuario.setId(rs.getInt(1));
            }
        }
    }

    public void update(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET email = ?, senha = ?, cliente_id = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getSenha());
            ps.setInt(3, usuario.getClienteId());
            ps.setInt(4, usuario.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Optional<Usuario> findByEmailAndSenha(String email, String senha) throws SQLException {
        String sql = "SELECT id, email, senha, cliente_id FROM usuario WHERE email = ? AND senha = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario(
                            rs.getInt("id"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getInt("cliente_id")
                    );
                    return Optional.of(u);
                }
            }
        }
        return Optional.empty();
    }
}