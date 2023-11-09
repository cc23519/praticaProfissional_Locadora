package com.locadora.locadora;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;


public class telaErroBancoController {

    @FXML
    private Button buttonErroTentarNovamente;

    @FXML
    private Button buttonErroSair;

    @FXML
    private ProgressBar erroProgressBar;

    @FXML
    private void initialize(){
        buttonErroSair.setOnAction(event -> {
            Stage stage = (Stage) buttonErroSair.getScene().getWindow();
            stage.close();
        });

        buttonErroTentarNovamente.setOnAction(event -> {
            erroProgressBar.setVisible(true);

            Thread conexaoThread = new Thread(() -> {
                Connection connection = com.locadora.locadora.criarConexaoBanco.criarConexaoBancoDados();

                javafx.application.Platform.runLater(() -> {
                    if (connection != null) {
                        Stage stage = (Stage) buttonErroSair.getScene().getWindow();
                        stage.close();
                        Scene scene = null;
                        abrirTelas abrirTelas = new abrirTelas();
                        String tela = "telaLogin.FXML";
                        abrirTelas.abrirTela(tela, "Voyage - Sistema Interno");
                        System.out.println("Autenticação bem-sucedida");
                    } else {
                        erroProgressBar.setVisible(false);
                        System.out.println("Erro");
                    }
                });
            });

            conexaoThread.start();
        });
    }
}
