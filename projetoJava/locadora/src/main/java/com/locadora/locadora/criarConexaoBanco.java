/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locadora.locadora;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class criarConexaoBanco {
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
    }

}


    
 
