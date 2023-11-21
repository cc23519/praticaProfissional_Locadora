package com.locadora.locadora.ClassesAlterações;

import static com.locadora.locadora.criarConexaoBanco.criarConexaoBancoDados;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class alterarClientes {
        public int alterarCliente (String usuario, String nome, String sobrenome, String tipo, String ddd, String telefone, String rua, String numero,
        String bairro, String cidade, String estado, String cep, String complemento, Integer id) {
        int resultado = -1;
        try (Connection conn = criarConexaoBancoDados();
             CallableStatement stmt = conn.prepareCall("{call stAlterarClientes(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {

            stmt.setString(1, usuario);
            stmt.setInt(2, id);
            stmt.setString(3, nome);
            stmt.setString(2, sobrenome);
            stmt.setString(3, tipo);
            stmt.setString(4, ddd);
            stmt.setString(5, telefone);
            stmt.setString(6, rua);
            stmt.setString(7, numero);
            stmt.setString(8, bairro);
            stmt.setString(9, cidade);
            stmt.setString(10, estado);
            stmt.setString(11, cep);
            stmt.setString(12, complemento);

            stmt.execute();

            resultado = stmt.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
