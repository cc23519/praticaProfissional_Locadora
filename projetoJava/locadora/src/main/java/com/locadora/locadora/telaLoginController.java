package com.locadora.locadora;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class telaLoginController {
    @FXML
    private TextField fieldLogin;

    @FXML
    private TextField passwordSenha;

    @FXML
    private Button buttonEntrar;
    
    public String login2;
    
    @FXML
    public void initialize() {
        buttonEntrar.setOnAction(event -> {
            String login = fieldLogin.getText();
            String senha = passwordSenha.getText();

            autenticarUsuario autenticarUsuario = new autenticarUsuario();
            if (autenticarUsuario.autenticarUsuario(login, senha)) {

                usuario.setUsername(login);
                Stage telaLoginStage = (Stage) buttonEntrar.getScene().getWindow();
                telaLoginStage.close();
                abrirTelas abrirTelas = new abrirTelas();
                String tela = "telaConsultaExcluir.fxml";
                abrirTelas.abrirTela(tela, "Voyage - Excluir");
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