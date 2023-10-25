/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locadora.locadora;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class telaCadastroController {
    // Carro
    @FXML
    public TextField inputModeloCarro;
    
    @FXML
    public TextField inputPlacaCarro;
    
    @FXML
    public TextField inputAnoCarro;
    
    @FXML
    public TextField inputChassiCarro;
    
    @FXML
    public TextField inputPrecoDiariaCarro;
    
    @FXML
    public Button buttonGerarCarro;
    
    @FXML
    public Label labelIdCarro;
    
    @FXML
    public Label labelStatus;
    
    
    // Seguro
    @FXML
    public TextField inputTipoSeguro;
    
    @FXML
    public TextField inputPrecoSeguro;
    
    @FXML
    public TextField inputDescricaoSeguro;
    
    @FXML
    public Button buttonGerarSeguro;
    
    @FXML
    public Label labelIdSeguro;
}
