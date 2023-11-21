package com.locadora.locadora.ClassesAlterações;

import static com.locadora.locadora.criarConexaoBanco.criarConexaoBancoDados;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class alterarCarros {
         public int alterarCarro (String usuario, Integer id, String chassi, String placa, String modelo, BigDecimal preco) {
        int resultado = -1;
        try (Connection conn = criarConexaoBancoDados();
             CallableStatement stmt = conn.prepareCall("{call stAlterarCarros(?, ?, ?, ?, ?, ?, ?)}")) {

            stmt.setString(1, usuario);
            stmt.setInt(2, id);
            stmt.setString(3, chassi);
            stmt.setString(4, placa);
            stmt.setString(5, modelo);
            stmt.setBigDecimal(6, preco);
            stmt.registerOutParameter(7, java.sql.Types.INTEGER);

            stmt.execute();

            resultado = stmt.getInt(7);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}