package br.com.aula.prova2.controller;


import br.com.aula.prova2.dao.ClienteDao;
import br.com.aula.prova2.dao.UsuarioDao;
import br.com.aula.prova2.model.Cliente;
import br.com.aula.prova2.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class AuthController {

    @FXML private AnchorPane loginPane, registerPane;
    // LOGIN fields
    @FXML private TextField loginEmail;
    @FXML private PasswordField loginPassword;
    // REGISTER fields
    @FXML private TextField regName, regEmail, regAddress;
    @FXML private PasswordField regPassword;

    private final UsuarioDao usuarioDao = new UsuarioDao();
    private final ClienteDao clienteDao = new ClienteDao();

    @FXML
    public void initialize() {
        // garante que inicia em login
        loginPane.setVisible(true);
        registerPane.setVisible(false);
    }

    @FXML
    private void showRegister(ActionEvent evt) {
        loginPane.setVisible(false);
        registerPane.setVisible(true);
    }

    @FXML
    private void showLogin(ActionEvent evt) {
        registerPane.setVisible(false);
        loginPane.setVisible(true);
    }

    @FXML
    private void onLogin(ActionEvent evt) {
        String email = loginEmail.getText().trim();
        String senha = loginPassword.getText().trim();
        if (email.isEmpty() || senha.isEmpty()) {
            alert(Alert.AlertType.WARNING, "Preencha e-mail e senha");
            return;
        }

        try {
            Optional<Usuario> ou = usuarioDao.findByEmailAndSenha(email, senha);
            if (ou.isPresent()) {
                // **Login OK**: carrega a cena de produtos
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/br/com/aula/prova2/view/products-view.fxml")
                );
                Parent root = loader.load();

                Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
                stage.setTitle("Produtos");
                stage.setScene(new Scene(root));
                stage.show();

            } else {
                alert(Alert.AlertType.ERROR, "E-mail ou senha inválidos");
            }

        } catch (SQLException e) {
            alert(Alert.AlertType.ERROR, "Erro ao autenticar: " + e.getMessage());

        } catch (IOException e) {
            e.printStackTrace();
            alert(Alert.AlertType.ERROR, "Erro ao abrir tela de produtos: " + e.getMessage());
        }
    }

    @FXML
    private void onRegister(ActionEvent evt) {
        String nome = regName.getText().trim();
        String email = regEmail.getText().trim();
        String senha = regPassword.getText().trim();
        String endereco = regAddress.getText().trim();
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || endereco.isEmpty()) {
            alert(Alert.AlertType.WARNING, "Preencha todos os campos");
            return;
        }
        try {
            Cliente c = new Cliente();
            c.setNome(nome);
            c.setEndereco(endereco);
            clienteDao.insert(c);

            Usuario u = new Usuario();
            u.setEmail(email);
            u.setSenha(senha);
            u.setClienteId(c.getId());
            usuarioDao.insert(u);

            alert(Alert.AlertType.INFORMATION, "Cadastro realizado! Faça login.");
            showLogin(evt);

        } catch (SQLException e) {
            alert(Alert.AlertType.ERROR, "Erro no cadastro: " + e.getMessage());
        }
    }

    private void alert(Alert.AlertType type, String msg) {
        Alert a = new Alert(type, msg, ButtonType.OK);
        a.showAndWait();
    }
}