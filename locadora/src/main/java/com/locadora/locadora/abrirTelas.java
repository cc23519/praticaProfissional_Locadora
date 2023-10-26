/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locadora.locadora;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author u23519
 */
public class abrirTelas {
        public void abrirTela(String nomedocumento) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nomedocumento));
            Parent root = loader.load();
            Stage novaTela = new Stage();
            novaTela.setTitle("VOYAGE - Consulta de dados");
            novaTela.setScene(new Scene(root));
            novaTela.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
