package br.com.aula.prova2.dao;


import br.com.aula.prova2.db.ConnectionFactory;
import br.com.aula.prova2.model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao {

    public int insert(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedido (cliente_id, total) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, pedido.getClienteId());
            ps.setBigDecimal(2, pedido.getTotal());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    pedido.setId(id);
                    return id;
                }
            }
        }
        throw new SQLException("Falha ao inserir pedido, sem chave gerada.");
    }

    public List<Pedido> findByClienteId(int clienteId) throws SQLException {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT id, cliente_id, data_pedido, total FROM pedido WHERE cliente_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Pedido(
                            rs.getInt("id"),
                            rs.getInt("cliente_id"),
                            rs.getTimestamp("data_pedido").toLocalDateTime(),
                            rs.getBigDecimal("total")
                    ));
                }
            }
        }
        return lista;
    }
}