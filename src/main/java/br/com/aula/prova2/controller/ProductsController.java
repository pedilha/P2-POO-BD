package br.com.aula.prova2.controller;


import br.com.aula.prova2.dao.ProdutoDao;
import br.com.aula.prova2.model.Produto;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class ProductsController {

    @FXML private TableView<Produto> productTable;
    @FXML private TableColumn<Produto, Integer> colId;
    @FXML private TableColumn<Produto, String>  colName;
    @FXML private TableColumn<Produto, String>  colDesc;
    @FXML private TableColumn<Produto, Double>  colPrice;

    private final ProdutoDao produtoDao = new ProdutoDao();

    @FXML
    public void initialize() throws SQLException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("preco"));

        productTable.setItems(FXCollections.observableArrayList(
                produtoDao.findAll()
        ));
    }

    @FXML
    private void onAddToCart() {
        Produto selecionado = productTable.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            new Alert(Alert.AlertType.WARNING, "Selecione um produto!").showAndWait();
            return;
        }
        new Alert(Alert.AlertType.INFORMATION,
                "Produto \"" + selecionado.getNome() + "\" adicionado ao carrinho!"
        ).showAndWait();
    }
}