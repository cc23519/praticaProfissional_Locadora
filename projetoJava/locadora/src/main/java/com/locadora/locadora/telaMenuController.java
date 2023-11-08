package com.locadora.locadora;

import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.Scene;

public class telaMenuController {
    
    @FXML
    public Button buttonSair;

    @FXML
    public Button buttonConsultar;

    @FXML
    public Button buttonCadastrar;

    @FXML
    public Button buttonExcluir;

    @FXML
    public Button buttonCadastrarColab;


    @FXML
    public void initialize(){
        buttonSair.setOnAction(event -> {
            Stage stage = (Stage) buttonSair.getScene().getWindow();
            stage.close();
        });

        buttonConsultar.setOnAction(event -> {
            abrirTelas abrirTelas = new abrirTelas();
            String tela = "telaConsultaAllFunc.FXML";
            abrirTelas.abrirTela(tela, "Voyage - Consultar");
        });

        buttonCadastrar.setOnAction(event -> {
            abrirTelas abrirTelas = new abrirTelas();
            String tela = "telaCadastro.FXML";
            abrirTelas.abrirTela(tela, "Voyage - Cadastrar");
        });

        buttonExcluir.setOnAction(event -> {
            abrirTelas abrirTelas = new abrirTelas();
            String tela = "telaConsultaExcluir.fxml";
            abrirTelas.abrirTela(tela, "Voyage - Excluir");
        });

        buttonCadastrarColab.setOnAction(event -> {
            abrirTelas abrirTelas = new abrirTelas();
            String tela = "telaCadastroColaboradores.FXML";
            abrirTelas.abrirTela(tela, "Voyage - Cadastrar colaboradores");
        });
    }
}
