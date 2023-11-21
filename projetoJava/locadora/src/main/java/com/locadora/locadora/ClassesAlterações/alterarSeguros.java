package com.locadora.locadora.ClassesAlterações;

import static com.locadora.locadora.criarConexaoBanco.criarConexaoBancoDados;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class alterarSeguros {
    public int alterarSeguro(String usuario, String tipo, String descricao, BigDecimal preco, Integer id) {
        int resultado = -1;
        try (Connection conn = criarConexaoBancoDados();
             CallableStatement stmt = conn.prepareCall("{call stAlterarSeguros(?, ?, ?, ?, ?, ?)}")) {

            stmt.setString(1, usuario);
            stmt.setInt(2, id);
            stmt.setString(3, tipo);
            stmt.setString(4, descricao);
            stmt.setBigDecimal(5, preco);
            stmt.registerOutParameter(6, java.sql.Types.INTEGER);

            stmt.execute();

            resultado = stmt.getInt(6);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}

