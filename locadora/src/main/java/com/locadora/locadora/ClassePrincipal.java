package com.locadora.locadora;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;

public class ClassePrincipal extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Connection connection = com.locadora.locadora.criarConexaoBanco.criarConexaoBancoDados();

        if (connection != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(ClassePrincipal.class.getResource("telaLogin.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setResizable(false);
            stage.setTitle("LocaCar");
            stage.setScene(scene);
            stage.show();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(ClassePrincipal.class.getResource("erroBancoDeDados.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Erro de Conexão");
            stage.setScene(scene);
            stage.show();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
