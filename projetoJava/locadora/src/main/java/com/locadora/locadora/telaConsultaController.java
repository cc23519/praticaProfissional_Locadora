package com.locadora.locadora;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;

public class telaConsultaController{
    @FXML
    private TableView<Cliente> tabelaCliente;
    @FXML
    public Label statusConsulta;
    @FXML
    private TableColumn<Cliente, Integer> columnidClientes;
    @FXML
    private TableColumn<Cliente, String> columnnomeCliente;
    @FXML
    private TableColumn<Cliente, String> columncpfCliente;
    @FXML
    private TableColumn<Cliente, String> columntipoCliente;
    @FXML
    private TableColumn<Cliente, String> columntelefoneCliente;
    @FXML
    private TableColumn<Cliente, String> columnendereco1Cliente;
    @FXML
    private TableColumn<Cliente, String> columnendereco2Cliente;

    @FXML
    public void initialize() {
        Connection connection = criarConexaoBanco.criarConexaoBancoDados();
        if (connection != null) {
            preencherTabelaClientes(connection, tabelaCliente);
        } else {
            statusConsulta.setVisible(true);
            statusConsulta.setText("Não foi possível conectar ao banco de dados.");
        }
    }

    @FXML
    public void preencherTabelaClientes(Connection connection, TableView<Cliente> tabela) {
        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT * from VconsultaClientes";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                statusConsulta.setVisible(true);
                statusConsulta.setText("Realizando consulta...");
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String cpf = resultSet.getString("cpf");
                String tipoTelefone = resultSet.getString("tipo_telefone");
                String telefone = resultSet.getString("telefone");
                String endereco1 = resultSet.getString("endereco1");
                String endereco2 = resultSet.getString("endereco2");

                Cliente cliente = new Cliente(id, nome, cpf, tipoTelefone, telefone, endereco1, endereco2);
                clientes.add(cliente);
            }

            tabela.setItems(FXCollections.observableArrayList(clientes));
            statusConsulta.setVisible(true);
            statusConsulta.setText("Consulta realizada");
        } catch (SQLException e) {
            statusConsulta.setVisible(true);
            statusConsulta.setText("Ocorreu um erro: " + e);
        }
    }
}
