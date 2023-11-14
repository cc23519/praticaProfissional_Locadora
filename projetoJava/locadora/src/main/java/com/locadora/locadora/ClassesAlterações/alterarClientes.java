package com.locadora.locadora.ClassesAlterações;

import static com.locadora.locadora.criarConexaoBanco.criarConexaoBancoDados;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class alterarClientes {
        public int alterarCliente (String nome, String sobrenome, String tipo, String telefone, String rua, Integer numero,
        String bairro, String cidade, String estado, String cep, String complemento) {
        int resultado = -1;
        try (Connection conn = criarConexaoBancoDados();
             CallableStatement stmt = conn.prepareCall("{call stExcluirLocacoes(?, ?)}")) {

            stmt.setString(1, nome);
            stmt.setString(2, sobrenome);
            stmt.setString(3, tipo);
            stmt.setString(4, telefone);
            stmt.setString(5, rua);
            stmt.setInt(6, numero);
            stmt.setString(7, bairro);
            stmt.setString(8, cidade);
            stmt.setString(9, estado);
            stmt.setString(10, cep);
            stmt.setString(11, complemento);

            stmt.execute();

            resultado = stmt.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
