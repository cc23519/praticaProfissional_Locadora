/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locadora.locadora;

import static com.locadora.locadora.criarConexaoBanco.criarConexaoBancoDados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;


public class autenticarUsuario {
    public static boolean autenticarUsuario(String username, String senha) {
    criarConexaoBanco criarConexaoBanco = new criarConexaoBanco();
    Connection conn = criarConexaoBancoDados();

    if (conn != null) {
        String sql = "SELECT senhaColab FROM esquemaLocadora.tabelaColaboradorCred WHERE usuarioColab = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String senhaDoBanco = rs.getString("senhaColab");

                if (BCrypt.checkpw(senha, senhaDoBanco)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return false;
}
}
