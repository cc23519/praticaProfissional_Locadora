package com.locadora.locadora.ClassesAlterações;

import static com.locadora.locadora.criarConexaoBanco.criarConexaoBancoDados;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class alterarCarros {
         public int alterarCarro (String chassi, String placa, String modelo, String ano, String preco) {
        int resultado = -1;
        try (Connection conn = criarConexaoBancoDados();
             CallableStatement stmt = conn.prepareCall("{call stExcluirLocacoes(?, ?)}")) {

            stmt.setString(1, chassi);
            stmt.setString(2, placa);
            stmt.setString(3, modelo);
            stmt.setString(4, ano);
            stmt.setString(5, preco);

            stmt.execute();

            resultado = stmt.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}