package br.com.aula.prova2.dao;


import br.com.aula.prova2.db.ConnectionFactory;
import br.com.aula.prova2.model.ItemPedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoDao {

    public void insert(ItemPedido item) throws SQLException {
        String sql = "INSERT INTO item_pedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, item.getPedidoId());
            ps.setInt(2, item.getProdutoId());
            ps.setInt(3, item.getQuantidade());
            ps.setBigDecimal(4, item.getPrecoUnitario());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) item.setId(rs.getInt(1));
            }
        }
    }

    public List<ItemPedido> findByPedidoId(int pedidoId) throws SQLException {
        List<ItemPedido> lista = new ArrayList<>();
        String sql = "SELECT id, pedido_id, produto_id, quantidade, preco_unitario FROM item_pedido WHERE pedido_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pedidoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new ItemPedido(
                            rs.getInt("id"),
                            rs.getInt("pedido_id"),
                            rs.getInt("produto_id"),
                            rs.getInt("quantidade"),
                            rs.getBigDecimal("preco_unitario")
                    ));
                }
            }
        }
        return lista;
    }
}