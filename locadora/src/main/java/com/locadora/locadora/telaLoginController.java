/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locadora.locadora;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

        });
    }
}
