package com.locadora.locadora.controladoresAlterações;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class telaClienteController {
    @FXML
    public TextField textfieldNome;
    
    @FXML
    public TextField textfieldSobrenome;
            
    @FXML
    public TextField textfieldTelefone;
            
    @FXML
    public TextField textfieldRua;
            
    @FXML
    public TextField textfieldNumero;
            
    @FXML
    public TextField textfieldBairro;
            
    @FXML
    public TextField textfieldCidade;
            
    @FXML
    public TextField textfieldCep;
    
    @FXML
    public TextField textfieldComplemento;
    
    @FXML
    public ComboBox comboboxTipo, comboboxEstado;
    
    @FXML
    public Button buttonFinalizar;
    
    @FXML
    public void initialize(){

    }
}
