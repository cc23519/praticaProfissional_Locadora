package com.locadora.locadora;

import static com.locadora.locadora.criarConexaoBanco.criarConexaoBancoDados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.mindrot.jbcrypt.BCrypt;

public class inserirSeguro {
public int inserirSeguro(String tipo, String descricao, Float preco) {
    int idSeguro = -1;

    String sqlSeguro = "INSERT INTO esquemaLocadora.tabelaSeguros(tipoSeguro, descricaoSeguro, precoSeguro) VALUES(?, ?, ?)";

    try (Connection conn = criarConexaoBancoDados();
         PreparedStatement stmtSeguros = conn.prepareStatement(sqlSeguro, Statement.RETURN_GENERATED_KEYS)){

        stmtSeguros.setString(1, tipo);
        stmtSeguros.setString(2, descricao);
        stmtSeguros.setFloat(3, preco);
        stmtSeguros.executeUpdate();

        ResultSet generatedKeys = stmtSeguros.getGeneratedKeys();
        if (generatedKeys.next()) {
            idSeguro = generatedKeys.getInt(1);
        }

        if (idSeguro != -1) {
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return idSeguro;
}
}
