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
            
            if (autenticarUsuario(login, senha)) {
                abrirTelaConsulta();
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
    
private boolean autenticarUsuario(String username, String senha) {
    criarConexaoBanco criarConexaoBanco = new criarConexaoBanco();
    Connection conn = criarConexaoBancoDados();

    if (conn != null) {
        String sql = "SELECT senhaColab FROM esquemaLocadora.tabelaColaboradorCred WHERE usuarioColab = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String senhaDoBanco = rs.getString("senhaColab");

                if (BCrypt.checkpw(senha, senhaDoBanco)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return false;
}


    private void abrirTelaConsulta() {
        try {
            Stage telaLoginStage = (Stage) buttonEntrar.getScene().getWindow();
            telaLoginStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telaCadastroColaboradores.fxml"));
            Parent root = loader.load();
            telaCadastroColaboradoresController controller = loader.getController();
            Stage novaTela = new Stage();
            novaTela.setTitle("VOYAGE - Consulta de dados");
            novaTela.setScene(new Scene(root));
            novaTela.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 


