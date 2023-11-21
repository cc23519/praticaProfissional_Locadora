package com.locadora.locadora;

import com.locadora.locadora.controladoresAlterações.telaCarroController;
import com.locadora.locadora.controladoresAlterações.telaClienteController;
import com.locadora.locadora.controladoresAlterações.telaSeguroController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class abrirTelas {

    public void abrirTela(String nomedocumento, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nomedocumento));
            Parent root = loader.load();
            Stage novaTela = new Stage();
            novaTela.setTitle(titulo);
            novaTela.setScene(new Scene(root));
            novaTela.setResizable(false);
            novaTela.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void abrirTelaComDadosCarro(String nomedocumento, String titulo, Object... dadosCarro) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nomedocumento));
            Parent root = loader.load();

            telaCarroController controlador = loader.getController();

            controlador.inicializarDadosCarro(dadosCarro);

            Stage novaTela = new Stage();
            novaTela.setTitle(titulo);
            novaTela.setScene(new Scene(root));
            novaTela.setResizable(false);
            novaTela.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void abrirTelaComDadosSeguros(String nomedocumento, String titulo, Object... dadosSeguro) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nomedocumento));
            Parent root = loader.load();

            telaSeguroController controlador = loader.getController();

            controlador.inicializarDadosSeguro(dadosSeguro);

            Stage novaTela = new Stage();
            novaTela.setTitle(titulo);
            novaTela.setScene(new Scene(root));
            novaTela.setResizable(false);
            novaTela.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void abrirTelaComDadosCliente(String nomedocumento, String titulo, Object... dadosCliente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nomedocumento));
            Parent root = loader.load();

            telaSeguroController controlador = loader.getController();

            controlador.inicializarDadosSeguro(dadosCliente);

            Stage novaTela = new Stage();
            novaTela.setTitle(titulo);
            novaTela.setScene(new Scene(root));
            novaTela.setResizable(false);
            novaTela.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
