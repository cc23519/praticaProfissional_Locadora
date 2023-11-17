package com.locadora.locadora.controladoresAlterações;


import com.locadora.locadora.Seguro;
import com.locadora.locadora.usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class telaSeguroController {
    
    private Integer id;
    private String tipo, preco, descricao, loginuser;
    
    @FXML
    public TextField textfieldTipoSeguro;
    
    @FXML
    public TextField textfielddescricaoSeguro;
    
    @FXML
    public TextField textfieldPrecoSeguro;
    
    @FXML
    public Button buttonFinalizarSeguro;
    
    @FXML
    public Button buttonSair;
    
    @FXML
    public Label statusSeguro;
    
    
    @FXML
    public void initialize() {
        
    }

    @FXML
    public void inicializarDadosSeguro(Object... dadosSeguro) {
        if (dadosSeguro.length > 0 && dadosSeguro[0] instanceof Seguro) {
            loginuser = usuario.getUsername();
            Seguro seguro = (Seguro) dadosSeguro[0];
            id = seguro.getId();
            tipo = seguro.getTipo();
            preco = seguro.getPreco();
            descricao = seguro.getDescricao();
        }
    }  
}
