package com.locadora.locadora;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClassePrincipal extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Connection connection = criarConexaoBancoDados();

        if (connection != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(ClassePrincipal.class.getResource("telaLogin.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setResizable(false);
            stage.setTitle("LocaCar");
            stage.setScene(scene);
            stage.show();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(ClassePrincipal.class.getResource("erroBancoDeDados.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Erro de Conexão");
            stage.setScene(scene);
            stage.show();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public static Connection criarConexaoBancoDados() {
        String jdbcUrl = "jdbc:sqlserver://regulus.cotuca.unicamp.br:1433;databaseName=BD23519;encrypt=true;trustServerCertificate=true";
        String usuario = "BD23519";
        String senha = "BD23519";
        Connection connection = null;
        System.out.println("Valor de jdbcUrl: " + jdbcUrl);

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(jdbcUrl, usuario, senha);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado: " + e.getMessage());
        }

        return connection;

        
    }}
