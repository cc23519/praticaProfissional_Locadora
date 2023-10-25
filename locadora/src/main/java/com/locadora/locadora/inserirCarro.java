package com.locadora.locadora;

import static com.locadora.locadora.criarConexaoBanco.criarConexaoBancoDados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.mindrot.jbcrypt.BCrypt;

public class inserirCarro {
public int inserirCarro(String nome, String sobrenome, String tipoAcesso, String usuarioColab, String senha) {
    String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());
    int idColaborador = -1;

    String sqlColaborador = "INSERT INTO esquemaLocadora.tabelaColaborador (nomeColab, sobrenomeColab, tipoAcesso) VALUES (?, ?, ?)";
    String sqlCredencial = "INSERT INTO esquemaLocadora.tabelaColaboradorCred (usuarioColab, senhaColab) VALUES (?, ?)";

    try (Connection conn = criarConexaoBancoDados();
         PreparedStatement stmtColaborador = conn.prepareStatement(sqlColaborador, Statement.RETURN_GENERATED_KEYS);
         PreparedStatement stmtCredencial = conn.prepareStatement(sqlCredencial)) {

        stmtColaborador.setString(1, nome);
        stmtColaborador.setString(2, sobrenome);
        stmtColaborador.setString(3, tipoAcesso);
        stmtColaborador.executeUpdate();

        ResultSet generatedKeys = stmtColaborador.getGeneratedKeys();
        if (generatedKeys.next()) {
            idColaborador = generatedKeys.getInt(1);
        }

        if (idColaborador != -1) {
            stmtCredencial.setString(1, usuarioColab);
            stmtCredencial.setString(2, senhaHash);
            stmtCredencial.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return idColaborador;
}
}
