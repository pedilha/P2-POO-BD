package br.com.aula.prova2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/br/com/aula/prova2/view/auth-view.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("PDV - Login / Cadastro");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}