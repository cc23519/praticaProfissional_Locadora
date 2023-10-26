/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locadora.locadora;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class telaCadastroColaboradoresController {
    @FXML
    public TextField fieldPrimeiroNome;
    
    @FXML
    public TextField fieldSegundoNome;
    
    @FXML
    public TextField fieldUser;
    
    @FXML
    public TextField fieldSenha;
    
    @FXML
    public ComboBox comboboxTipo;
    
    @FXML
    public Button buttonCriarColaborador;
    
    @FXML
    public Label visorIdColaborador;
    
    @FXML
    public Label statusTelaColaborador;
    
    @FXML
    public void initialize(){
        ObservableList<String> Opcoes = FXCollections.observableArrayList("Consulta", "Moderador");
        comboboxTipo.setItems(Opcoes);
        comboboxTipo.setValue("Consulta");
        
        comboboxTipo.setOnAction(event ->{
            String tipoColaborador = (String) comboboxTipo.getValue();
        });
        
        buttonCriarColaborador.setOnAction(event -> {
            String primeironome = fieldPrimeiroNome.getText();
            String sobrenome = fieldSegundoNome.getText();
            String user = fieldUser.getText();
            String senha = fieldSenha.getText();
            String tipoColaborador = (String) comboboxTipo.getValue();
            
            inserirColaborador inserirColaborador = new inserirColaborador();
            int idColaborador = inserirColaborador.inserirColaborador(primeironome, sobrenome, tipoColaborador, user, senha);

            if (idColaborador != -1) {
                String id = Integer.toString(idColaborador);
                visorIdColaborador.setVisible(true);
                visorIdColaborador.setText(id);
                statusTelaColaborador.setText("Colaborador Criado com sucesso!");
            } else {
                statusTelaColaborador.setText("Falha ao criar o colaborador.");
            }
        });
    }
}
