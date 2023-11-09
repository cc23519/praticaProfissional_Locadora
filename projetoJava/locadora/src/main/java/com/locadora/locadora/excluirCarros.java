package com.locadora.locadora;

import static com.locadora.locadora.criarConexaoBanco.criarConexaoBancoDados;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class excluirCarros {
    public int excluirCarro(String usuario, int idCarro)  {
    int resultado = -1;
    try (Connection conn = criarConexaoBancoDados();
         CallableStatement stmt = conn.prepareCall("{call stExcluirCarros(?, ?, ?)}")) {

            stmt.setString(1, usuario);
            stmt.setInt(2, idCarro);
            stmt.registerOutParameter(3, java.sql.Types.INTEGER);

            stmt.execute();

            resultado = stmt.getInt(3);

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return resultado;
}
}
