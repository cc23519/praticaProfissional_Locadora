package com.locadora.locadora.ClassesAlterações;

import static com.locadora.locadora.criarConexaoBanco.criarConexaoBancoDados;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class alterarSeguros {
         public int alterarSeguro (String tipo, String descricao, Integer preco, Integer id) {
        int resultado = -1;
        try (Connection conn = criarConexaoBancoDados();
             CallableStatement stmt = conn.prepareCall("{call stExcluirLocacoes(?, ?)}")) {

            stmt.setString(1, tipo);
            stmt.setString(2, descricao);
            stmt.setInt(3, preco);
            stmt.setInt(4, id);

            stmt.execute();

            resultado = stmt.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
