package com.locadora.locadora.controladoresAlterações;

import com.locadora.locadora.Carro;
import com.locadora.locadora.ClassesAlterações.alterarCarros;
import com.locadora.locadora.ClassesAlterações.alterarSeguros;
import com.locadora.locadora.usuario;
import java.math.BigDecimal;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class telaCarroController {

    public String loginuser; 
    public Integer id;
    public String modelo;
    public String placa;
    public String ano;
    public String chassi;
    public String valor;
    
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
        buttonSair.setOnAction(event -> {
            Stage stage = (Stage) buttonSair.getScene().getWindow();
            stage.close();
        });
        
        buttonFinalizarCarro.setOnAction(event -> {
            AlterarCarro();
        });
    }


    
    @FXML
    public void inicializarDadosCarro(Object... dadosCarro) {
        if (dadosCarro.length > 0 && dadosCarro[0] instanceof Carro) {
            loginuser = usuario.getUsername();
            Carro carro = (Carro) dadosCarro[0];
            id = carro.getId();
            modelo = carro.getModelo();
            placa = carro.getPlaca();
            chassi = carro.getChassi();
            valor = carro.getValor();
            
            inserirDados(chassi, placa, modelo, valor);
        }
    }  
    public void inserirDados(String chassi, String placa, String modelo, String valor){
        textfieldChassiCarro.setText(chassi);
        textfieldPlacaCarro.setText(placa);
        textfieldModeloCarro.setText(modelo);
        textfieldPrecoCarro.setText(valor);
    }
    
    public void AlterarCarro(){
        loginuser = usuario.getUsername();
        String novochassi = textfieldChassiCarro.getText();
        String novoplaca = textfieldPlacaCarro.getText();
        String novomodelo = textfieldModeloCarro.getText();
        String novopreco = textfieldPrecoCarro.getText();
        
        BigDecimal preco = new BigDecimal(novopreco);
        
        alterarCarros alterar = new alterarCarros();
        Integer resultados = alterar.alterarCarro(loginuser,id, novochassi, novoplaca, novomodelo, preco);
        
        System.out.println(resultados);
        
        switch (resultados) {
            case 0:
                statusCarro.setVisible(true);
                statusCarro.setText("Você não tem acesso de moderador para realizar alteração de seguros.");
                break;
            case 4:
                statusCarro.setVisible(true);
                statusCarro.setText("Alteração realizada com sucesso.");
                break;
            default:
                statusCarro.setVisible(true);
                statusCarro.setText("Ocorreu um erro.");
                break;
        }
    }
}


