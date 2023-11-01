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
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableView<Carro> tabelaCarro;
    @FXML
    private TableColumn<Carro, Integer> columnidCarro;
    @FXML
    private TableColumn<Carro, String> columnmodeloCarro;
    @FXML
    private TableColumn<Carro, String> columnplacaCarro;
    @FXML
    private TableColumn<Carro, String> columnchassiCarro;
    @FXML
    private TableColumn<Carro, Double> columnvalorCarro;
    @FXML
    private TableColumn<Carro, String> columnstatusCarro;
    
    @FXML
    private TableView<Seguro> tabelaSeguro;
    @FXML
    private TableColumn<Seguro, Integer> columnidSeguro;
    @FXML
    private TableColumn<Seguro, String> columntipoSeguro;
    @FXML
    private TableColumn<Seguro, Double> columnPrecoSeguro;
    @FXML
    private TableColumn<Seguro, String> columndescricaoSeguro;

    @FXML
    public void initialize() {
        Connection connection = criarConexaoBanco.criarConexaoBancoDados();
        if (connection != null) {
            preencherTabelaClientes(connection, tabelaCliente);
            preencherTabelaCarro(connection, tabelaCarro);
            preencherTabelaSeguro(connection, tabelaSeguro);
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
                int id = resultSet.getInt("idClientes");
                String nome = resultSet.getString("NomeCompleto");
                String cpf = resultSet.getString("cpfCliente");
                String tipoTelefone = resultSet.getString("tipoTelefone");
                String telefone = resultSet.getString("contatoCliente");
                String endereco1 = resultSet.getString("Endereço1");
                String endereco2 = resultSet.getString("Endereco2");

                Cliente cliente = new Cliente(id, nome, cpf, tipoTelefone, telefone, endereco1, endereco2);
                clientes.add(cliente);
            }
            tabela.setItems(FXCollections.observableArrayList(clientes));
            columnidClientes.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnnomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
            columncpfCliente.setCellValueFactory(new PropertyValueFactory<>("cpf"));
            columntipoCliente.setCellValueFactory(new PropertyValueFactory<>("tipoTelefone"));
            columntelefoneCliente.setCellValueFactory(new PropertyValueFactory<>("telefone"));
            columnendereco1Cliente.setCellValueFactory(new PropertyValueFactory<>("endereco1"));
            columnendereco2Cliente.setCellValueFactory(new PropertyValueFactory<>("endereco2"));
            statusConsulta.setVisible(true);
            statusConsulta.setText("Consulta realizada");
        } catch (SQLException e) {
            statusConsulta.setVisible(true);
            statusConsulta.setText("Ocorreu um erro: " + e);
        }
    }
    
    @FXML
    public void preencherTabelaCarro(Connection connection, TableView<Carro> tabela) {
        List<Carro> carros = new ArrayList<>();

        String sql = "SELECT * FROM VconsultaCarros";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                statusConsulta.setVisible(true);
                statusConsulta.setText("Realizando consulta...");
                int id = resultSet.getInt("idCarro");
                String modelo = resultSet.getString("modeloCarro");
                String placa = resultSet.getString("placaCarro");
                String chassi = resultSet.getString("chassiCarro");
                String valor = resultSet.getString("precoDiaria_Carro");
                String status = resultSet.getString("status");

                Carro carro = new Carro(id, modelo, placa, chassi, valor, status);
                carros.add(carro);
            }

            tabela.setItems(FXCollections.observableArrayList(carros));
            columnidCarro.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnmodeloCarro.setCellValueFactory(new PropertyValueFactory<>("modelo"));
            columnplacaCarro.setCellValueFactory(new PropertyValueFactory<>("placa"));
            columnchassiCarro.setCellValueFactory(new PropertyValueFactory<>("chassi"));
            columnvalorCarro.setCellValueFactory(new PropertyValueFactory<>("valor"));
            columnstatusCarro.setCellValueFactory(new PropertyValueFactory<>("status"));
            statusConsulta.setVisible(true);
            statusConsulta.setText("Consulta realizada");
        } catch (SQLException e) {
            statusConsulta.setVisible(true);
            statusConsulta.setText("Ocorreu um erro: " + e);
        }
    }
    
    @FXML
    public void preencherTabelaSeguro(Connection connection, TableView<Seguro> tabela) {
        List<Seguro> seguros = new ArrayList<>();

        String sql = "SELECT * FROM VconsultaSeguro";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                statusConsulta.setVisible(true);
                statusConsulta.setText("Realizando consulta...");
                int id = resultSet.getInt("idSeguro");
                String tiposeguro = resultSet.getString("tipoSeguro");
                String descricao = resultSet.getString("descricaoSeguro");
                String preco = resultSet.getString("precoSeguro");

                Seguro seguro = new Seguro(id, tiposeguro, preco, descricao);
                seguros.add(seguro);
            }

            tabela.setItems(FXCollections.observableArrayList(seguros));
            columnidSeguro.setCellValueFactory(new PropertyValueFactory<>("id"));
            columntipoSeguro.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
            columndescricaoSeguro.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
            columnPrecoSeguro.setCellValueFactory(new PropertyValueFactory<>("Preco"));
            statusConsulta.setVisible(true);
            statusConsulta.setText("Consulta realizada");
        } catch (SQLException e) {
            statusConsulta.setVisible(true);
            statusConsulta.setText("Ocorreu um erro: " + e);
        }
    }
}
