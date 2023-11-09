package com.locadora.locadora;

import static com.locadora.locadora.criarConexaoBanco.criarConexaoBancoDados;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class excluirLocacoes {
     public int excluirLocacao(String usuario, int idSeguro) {
        int resultado = -1;
        try (Connection conn = criarConexaoBancoDados();
             CallableStatement stmt = conn.prepareCall("{call stExcluirLocacoes(?, ?)}")) {

            stmt.setString(1, usuario);
            stmt.setInt(2, idSeguro);

            stmt.execute();

            resultado = stmt.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
