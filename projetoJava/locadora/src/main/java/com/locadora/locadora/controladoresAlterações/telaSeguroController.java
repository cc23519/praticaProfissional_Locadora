package com.locadora.locadora.controladoresAlterações;


import com.locadora.locadora.Seguro;
import com.locadora.locadora.usuario;

public class telaSeguroController {
    
    private Integer id;
    private String tipo, preco, descricao, loginuser; 
    
    
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
