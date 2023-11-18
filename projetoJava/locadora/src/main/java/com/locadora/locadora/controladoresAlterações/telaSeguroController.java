package com.locadora.locadora.controladoresAlterações;

import com.locadora.locadora.ClassesAlterações.alterarSeguros;
import com.locadora.locadora.Seguro;
import com.locadora.locadora.usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        buttonSair.setOnAction(event -> {
            Stage stage = (Stage) buttonSair.getScene().getWindow();
            stage.close();
        });
        
        buttonFinalizarSeguro.setOnAction(event ->{
            AlterarSeguro();
        });
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
            
            inserirDados(tipo, preco, descricao);
        }
     
    }  
    
    public void inserirDados(String tipo, String descricao, String preco){
        textfieldTipoSeguro.setText(tipo);
        textfielddescricaoSeguro.setText(descricao);
        textfieldPrecoSeguro.setText(preco);
    }
    
    public void AlterarSeguro(){
        String novotipo = textfieldTipoSeguro.getText();
        String novodescricao = textfielddescricaoSeguro.getText();
        String novopreco = textfielddescricaoSeguro.getText();
        
        Integer novoprecoint = Integer.valueOf(novopreco);
        
        alterarSeguros alterar = new alterarSeguros();
        Integer resultados = alterar.alterarSeguro(novotipo,novodescricao, novoprecoint, id);
        
        System.out.println(resultados);
        
        switch (resultados) {
            case 0:
                statusSeguro.setVisible(true);
                statusSeguro.setText("Você não tem acesso de moderador para realizar alteração de seguros.");
                break;
            case 4:
                statusSeguro.setVisible(true);
                statusSeguro.setText("Exclusão realizada com sucesso.");
                break;
            default:
                statusSeguro.setVisible(true);
                statusSeguro.setText("Ocorreu um erro.");
                break;
        }
    }
}
