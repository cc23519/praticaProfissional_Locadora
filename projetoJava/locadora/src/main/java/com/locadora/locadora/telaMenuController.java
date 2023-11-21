package com.locadora.locadora;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class telaMenuController {

    @FXML
    public Button buttonConsultar;

    @FXML
    public Button buttonCadastrar;

    @FXML
    public Button buttonExcluir;

    @FXML
    public Button buttonCadastrarColab;

    @FXML
    public Button buttonSair;

    @FXML
    public void initialize() {
        buttonSair.setOnAction(event -> {
            Stage stage = (Stage) buttonSair.getScene().getWindow();
            stage.close();
        });

        buttonConsultar.setOnAction(event -> {
            abrirTelas abrir = new abrirTelas();
            abrir.abrirTela("telaConsultaAllFunc.fxml", "Voyage - Consultar");
        });

        buttonCadastrar.setOnAction(event -> {
            abrirTelas abrir = new abrirTelas();
            abrir.abrirTela("telaCadastro.fxml", "Voyage - Cadastrar");
        });

        buttonExcluir.setOnAction(event -> {
            abrirTelas abrir = new abrirTelas();
            abrir.abrirTela("telaConsultaExcluir.fxml", "Voyage - Excluir");
        });
        buttonCadastrarColab.setOnAction(event -> {
            abrirTelas abrir = new abrirTelas();
            abrir.abrirTela("telaCadastroColaboradores.fxml", "Voyage - Cadastrar colaboradores");
        });
    }
}
