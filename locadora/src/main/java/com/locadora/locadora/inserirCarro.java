package com.locadora.locadora;

import static com.locadora.locadora.criarConexaoBanco.criarConexaoBancoDados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class inserirCarro {
public int inserirCarro(Integer chassi, String modelo, String placa, Integer anocarro, Float preco) {
    int idCarro = -1;

    String sqlCarro = "INSERT INTO esquemaLocadora.tabelaCarro(chassiCarro, modeloCarro, placaCarro, anoCarro, precoDiaria_Carro, status) VALUES(?, ?, ?, ?, ?, 'Disponivel')";

    try (Connection conn = criarConexaoBancoDados();
         PreparedStatement stmtCarro = conn.prepareStatement(sqlCarro, Statement.RETURN_GENERATED_KEYS)) {

        stmtCarro.setInt(1, chassi);
        stmtCarro.setString(2, modelo);
        stmtCarro.setString(3, placa);
        stmtCarro.setInt(4, anocarro);
        stmtCarro.setFloat(5, preco);

        int affectedRows = stmtCarro.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("A inserção falhou, nenhum registro foi adicionado.");
        }

        ResultSet generatedKeys = stmtCarro.getGeneratedKeys();
        if (generatedKeys.next()) {
            idCarro = generatedKeys.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return idCarro;
}
}
