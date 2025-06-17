package br.com.aula.prova2.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pedido {
    private Integer id;
    private Integer clienteId;
    private LocalDateTime dataPedido;
    private BigDecimal total;

    public Pedido() { }

    public Pedido(Integer id, Integer clienteId, LocalDateTime dataPedido, BigDecimal total) {
        this.id = id;
        this.clienteId = clienteId;
        this.dataPedido = dataPedido;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClienteId() {
        return clienteId;
    }
    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }
    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", dataPedido=" + dataPedido +
                ", total=" + total +
                '}';
    }
}
