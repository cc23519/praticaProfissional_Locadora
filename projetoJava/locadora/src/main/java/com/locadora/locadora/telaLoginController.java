/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locadora.locadora;

import static com.locadora.locadora.criarConexaoBanco.criarConexaoBancoDados;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class telaLoginController {
    @FXML
    private TextField fieldLogin;

    @FXML
    private TextField passwordSenha;

    @FXML
    private Button buttonEntrar;
    
    
    
    @FXML
    private void initialize() {
        buttonEntrar.setOnAction(event -> {
            String login = fieldLogin.getText();
            String senha = passwordSenha.getText();
            
            autenticarUsuario autenticarUsuario = new autenticarUsuario();
            if (autenticarUsuario.autenticarUsuario(login, senha)) {
                Stage telaLoginStage = (Stage) buttonEntrar.getScene().getWindow();
                telaLoginStage.close();
                abrirTelas abrirTelas = new abrirTelas();
                String tela = "telaCadastro.FXML";
                abrirTelas.abrirTela(tela, "Voyage - Cadastrar Seguros e Carros");
                System.out.println("Autenticação bem-sucedida");
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erro de Autenticação");
                alert.setHeaderText("Autenticação Falhou");
                alert.setContentText("Nome de usuário ou senha incorretos.");
                alert.showAndWait();
            }
        });
    }
}


