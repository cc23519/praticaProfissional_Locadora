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
            String tela = "telaLogin.fxml";
            abrirTelas abrirTelas = new abrirTelas();
            abrirTelas.abrirTela(tela, "Voyage - Sistema do Funcionário");
        } else {
            String tela = "erroBancoDeDados.fxml";
            abrirTelas abrirTelas = new abrirTelas();
            abrirTelas.abrirTela(tela, "Erro de Conexão");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
