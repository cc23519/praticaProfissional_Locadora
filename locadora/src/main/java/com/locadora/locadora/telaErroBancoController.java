/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
                Connection connection = com.locadora.locadora.ClassePrincipal.criarConexaoBancoDados();

                javafx.application.Platform.runLater(() -> {
                    if (connection != null) {
                        Stage stage = (Stage) buttonErroSair.getScene().getWindow();
                        stage.close();
                        FXMLLoader fxmlLoader = new FXMLLoader(ClassePrincipal.class.getResource("telaLogin.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(fxmlLoader.load());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        stage.setTitle("LocaCar");
                        stage.setScene(scene);
                        stage.show();
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
