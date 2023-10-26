/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locadora.locadora;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
    public TextArea inputDescricaoSeguro;
    
    @FXML
    public Button buttonGerarSeguro;
    
    @FXML
    public Label labelIdSeguro;
    
    
    @FXML
    public void initialize(){
        buttonGerarCarro.setOnAction(event ->{
            String ModeloCarro = inputModeloCarro.getText();
            String PlacaCarro = inputPlacaCarro.getText();
            String textoAnoCarro = inputAnoCarro.getText();
            String textoChassi = inputChassiCarro.getText();
            String textoPrecoDiaria = inputAnoCarro.getText();
            
            Integer AnoCarro = Integer.parseInt(textoAnoCarro);
            Integer Chassi = Integer.parseInt(textoChassi);
            Float PrecoDiaria = Float.parseFloat(textoPrecoDiaria);
            
            inserirCarro inserirCarro = new inserirCarro();
            int idCarro = inserirCarro.inserirCarro(Chassi, ModeloCarro, PlacaCarro, AnoCarro, PrecoDiaria);
            
            if (idCarro != -1) {
                String id = Integer.toString(idCarro);
                labelIdCarro.setVisible(true);
                labelIdCarro.setText(id);
                labelStatus.setText("Carro Criado com sucesso!");
            } else {
                labelStatus.setText("Falha ao criar novo carro.");
            }
        });
        
        buttonGerarSeguro.setOnAction(event ->{ 
            String tipoSeguro = inputTipoSeguro.getText();
            String descricaoSeguro = inputDescricaoSeguro.getText();
            String textoPrecoSeguro = inputPrecoSeguro.getText();
            
            Float PrecoSeguro = Float.parseFloat(textoPrecoSeguro);
            
            inserirSeguro inserirSeguro = new inserirSeguro();
            int idSeguro = inserirSeguro.inserirSeguro(tipoSeguro, descricaoSeguro, PrecoSeguro);
            
            if (idSeguro != -1) {
                String id = Integer.toString(idSeguro);
                labelIdSeguro.setVisible(true);
                labelIdSeguro.setText(id);
                labelStatus.setText("Seguro Criado com sucesso!");
            } else {
                labelStatus.setText("Falha ao criar novo Seguro.");
            }
        });
    }
    
}
