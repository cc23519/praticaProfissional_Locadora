package com.locadora.locadora.controladoresAlterações;

import com.locadora.locadora.Carro;
import com.locadora.locadora.usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class telaCarroController {

    private String loginuser; 
    private Integer id;
    private String modelo;
    private String placa;
    private String ano;
    private String chassi;
    private String valor;
    
    @FXML
    public TextField textfieldChassiCarro;
    
    @FXML
    public TextField textfieldPlacaCarro;
    
    @FXML 
    public TextField textfieldModeloCarro;
    
    @FXML
    public TextField textfieldAnoCarro;
    
    @FXML
    public TextField textfieldPrecoCarro;
    
    @FXML
    public Label statusCarro;
    
    @FXML
    public Button buttonFinalizarCarro;
    
    @FXML
    public Button buttonSair;

    @FXML
    public void initialize() {
        
    }

    @FXML
    public void inicializarDadosCarro(Object... dadosCarro) {
        if (dadosCarro.length > 0 && dadosCarro[0] instanceof Carro) {
            loginuser = usuario.getUsername();
            Carro carro = (Carro) dadosCarro[0];
            id = carro.getId();
            modelo = carro.getModelo();
            placa = carro.getPlaca();
            ano = carro.getAno();
            chassi = carro.getChassi();
            valor = carro.getValor();
        }
    }  
}

