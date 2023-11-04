package com.locadora.locadora;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class telaExcluirController {
    @FXML
    private Button buttonClientes;

    @FXML
    private Button buttonCarro;

    @FXML
    private Button buttonSeguros;

    @FXML
    private Button buttonLocaAtivas;

    @FXML
    private Button buttonLocaInativa;

    @FXML
    private Button buttonExcluirSelecionado;

    @FXML
    private ImageView imageView;
    
    @FXML
    private TabPane tabExclusao;
  
    @FXML
    private Label textStatus;

    @FXML
    private TableView<Cliente> tableCliente;

    @FXML
    private TableColumn<Cliente, Integer> colunmidCliente;

    @FXML
    private TableColumn<Cliente, String> colunmnomeCliente;

    @FXML
    private TableColumn<Cliente, String> columncpfCliente;

    @FXML
    private TableColumn<Cliente, String> colunmtipoCliente;

    @FXML
    private TableColumn<Cliente, String> colunmtelefoneCliente;

    @FXML
    private TableColumn<Cliente, String> colunmendereco1Cliente;

    @FXML
    private TableColumn<Cliente, String> colunmendereco2Cliente;

    @FXML
    private TableView<Carro> tableCarro;

    @FXML
    private TableColumn<Carro, Integer> colunmidCarro;

    @FXML
    private TableColumn<Carro, String> colunmmodeloCarro;

    @FXML
    private TableColumn<Carro, String> colunmplacaCarro;

    @FXML
    private TableColumn<Carro, String> colunmchassiCarro;

    @FXML
    private TableColumn<Carro, String> colunmvalorCarro;

    @FXML
    private TableColumn<Carro, String> colunmstatusCarro;

    @FXML
    private TableView<Seguro> tableSeguros;

    @FXML
    private TableColumn<Seguro, Integer> colunmidSeguro;

    @FXML
    private TableColumn<Seguro, String> colunmtipoSeguro;

    @FXML
    private TableColumn<Seguro, String> colunmvalorSeguro;

    @FXML
    private TableColumn<Seguro, String> colunmdescricaoSeguro;

    @FXML
    private TableView<LocacaoA> tableLocaAtivas;

    @FXML
    private TableColumn<LocacaoA, Integer> colunmidLocaAtiva;

    @FXML
    private TableColumn<LocacaoA, String> colunmnomeLocaAtiva;

    @FXML
    private TableColumn<LocacaoA, String> colunmseguroLocaAtiva;

    @FXML
    private TableColumn<LocacaoA, String> colunmcarroLocaAtiva;

    @FXML
    private TableColumn<LocacaoA, String> colunmcriacaoLocaAtiva;

    @FXML
    private TableColumn<LocacaoA, String> colunminicioLocaAtiva;

    @FXML
    private TableColumn<LocacaoA, String> colunmfinalLocaAtiva;

    @FXML
    private TableColumn<LocacaoA, String> colunmvalorLocaAtiva;

    @FXML
    private TableView<LocacaoI> tableLocaInativas;

    @FXML
    private TableColumn<LocacaoI, Integer> colunmidLocaInativa;

    @FXML
    private TableColumn<LocacaoI, String> colunmnomeLocaInativa;

    @FXML
    private TableColumn<LocacaoI, String> colunmseguroLocaInativa;

    @FXML
    private TableColumn<LocacaoI, String> colunmcarroLocaInativa;

    @FXML
    private TableColumn<LocacaoI, String> colunmcriacaoLocaInativa;

    @FXML
    private TableColumn<LocacaoI, String> colunminicioLocaInativa;

    @FXML
    private TableColumn<LocacaoI, String> colunmfinalLocaInativa;

    @FXML
    private TableColumn<LocacaoI, String> colunmvalorLocaInativa;

    @FXML
    private Tab tabCliente;
    
    @FXML
    private Tab tabCarro;
    
    @FXML
    private Tab tabSeguro;
    
    @FXML
    private Tab tabAtivas;
    
    @FXML
    private Tab tabInativas;

    @FXML
    public void initialize(){
        Connection connection = criarConexaoBanco.criarConexaoBancoDados();
        
        telaLoginController telaLoginController = new telaLoginController();
        String user = telaLoginController.getLogin();
        if (connection != null) {
            buttonClientes.setOnAction(event ->{
                ativarTab(tabCliente);
            });
            
            buttonCarro.setOnAction(event ->{
                ativarTab(tabCarro);
            });
            
            buttonSeguros.setOnAction(event ->{
                ativarTab(tabSeguro);
            });
            
            buttonLocaAtivas.setOnAction(event ->{
                ativarTab(tabAtivas);
            });
            
            buttonLocaInativa.setOnAction(event ->{
                ativarTab(tabInativas);
            });
            
            
            tabCliente.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    preencherTabelaClientes(connection, tableCliente);
                }
            });

            tabCarro.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    preencherTabelaCarro(connection, tableCarro);
                }
            });


            tabSeguro.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    preencherTabelaSeguro(connection, tableSeguros);
                }
            });

            tabAtivas.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    preencherTabelaLocacaoA(connection, tableLocaAtivas);
                }
            });
            
            tabInativas.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    preencherTabelaLocacaoI(connection, tableLocaInativas);
                }
            });
            
        } else {
            textStatus.setVisible(true);
            textStatus.setText("Não foi possível conectar ao banco de dados.");
        }
    }
    
    @FXML
    public void ativarTab(Tab tab){
        tabExclusao.getSelectionModel().select(tab);
    }

    @FXML
    public void preencherTabelaClientes(Connection connection, TableView<Cliente> tabela) {
        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT * from VconsultaClientes";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                textStatus.setVisible(true);
                textStatus.setText("Realizando consulta...");
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
            colunmidCliente.setCellValueFactory(new PropertyValueFactory<>("id"));
            colunmnomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
            columncpfCliente.setCellValueFactory(new PropertyValueFactory<>("cpf"));
            colunmtipoCliente.setCellValueFactory(new PropertyValueFactory<>("tipoTelefone"));
            colunmtelefoneCliente.setCellValueFactory(new PropertyValueFactory<>("telefone"));
            colunmendereco1Cliente.setCellValueFactory(new PropertyValueFactory<>("endereco1"));
            colunmendereco2Cliente.setCellValueFactory(new PropertyValueFactory<>("endereco2"));
            textStatus.setVisible(true);
            textStatus.setText("Consulta realizada");
        } catch (SQLException e) {
            textStatus.setVisible(true);
            textStatus.setText("Ocorreu um erro: " + e);
        }
    }
    
    @FXML
    public void preencherTabelaCarro(Connection connection, TableView<Carro> tabela) {
        List<Carro> carros = new ArrayList<>();

        String sql = "SELECT * FROM VconsultaCarros";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                textStatus.setVisible(true);
                textStatus.setText("Realizando consulta...");
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
            colunmidCarro.setCellValueFactory(new PropertyValueFactory<>("id"));
            colunmmodeloCarro.setCellValueFactory(new PropertyValueFactory<>("modelo"));
            colunmplacaCarro.setCellValueFactory(new PropertyValueFactory<>("placa"));
            colunmchassiCarro.setCellValueFactory(new PropertyValueFactory<>("chassi"));
            colunmvalorCarro.setCellValueFactory(new PropertyValueFactory<>("valor"));
            colunmstatusCarro.setCellValueFactory(new PropertyValueFactory<>("status"));
            textStatus.setVisible(true);
            textStatus.setText("Consulta realizada");
        } catch (SQLException e) {
            textStatus.setVisible(true);
            textStatus.setText("Ocorreu um erro: " + e);
        }
    }
    
    @FXML
    public void preencherTabelaSeguro(Connection connection, TableView<Seguro> tabela) {
        List<Seguro> seguros = new ArrayList<>();

        String sql = "SELECT * FROM VconsultaSeguro";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                textStatus.setVisible(true);
                textStatus.setText("Realizando consulta...");
                int id = resultSet.getInt("idSeguro");
                String tiposeguro = resultSet.getString("tipoSeguro");
                String descricao = resultSet.getString("descricaoSeguro");
                String preco = resultSet.getString("precoSeguro");

                Seguro seguro = new Seguro(id, tiposeguro, preco, descricao);
                seguros.add(seguro);
            }

            tabela.setItems(FXCollections.observableArrayList(seguros));
            colunmidSeguro.setCellValueFactory(new PropertyValueFactory<>("id"));
            colunmtipoSeguro.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
            colunmdescricaoSeguro.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
            colunmvalorSeguro.setCellValueFactory(new PropertyValueFactory<>("Preco"));
            textStatus.setVisible(true);
            textStatus.setText("Consulta realizada");
        } catch (SQLException e) {
            textStatus.setVisible(true);
            textStatus.setText("Ocorreu um erro: " + e);
        }
    }
    
    @FXML
    public void preencherTabelaLocacaoA(Connection connection, TableView<LocacaoA> tabela) {
        List<LocacaoA> locacoesativas = new ArrayList<>();

        String sql = "select * from VLocacaoAtiva";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                textStatus.setVisible(true);
                textStatus.setText("Realizando consulta...");
                int id = resultSet.getInt("idLocacao");
                String nome = resultSet.getString("NomeCompleto");
                String tipo = resultSet.getString("tipoSeguro");
                String modelo = resultSet.getString("modeloCarro");
                String criacao = resultSet.getString("dataCriacao");
                String inicio = resultSet.getString("dataInicio");
                String fim = resultSet.getString("dataFim");
                String valor = resultSet.getString("valorTotal");

                LocacaoA locacaoativa = new LocacaoA(id, nome, tipo, modelo, criacao, inicio, fim, valor);
                locacoesativas.add(locacaoativa);
            }

            tabela.setItems(FXCollections.observableArrayList(locacoesativas));
            colunmidLocaAtiva.setCellValueFactory(new PropertyValueFactory<>("id"));
            colunmnomeLocaAtiva.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunmseguroLocaAtiva.setCellValueFactory(new PropertyValueFactory<>("tipo"));
            colunmcarroLocaAtiva.setCellValueFactory(new PropertyValueFactory<>("modelo"));
            colunmcriacaoLocaAtiva.setCellValueFactory(new PropertyValueFactory<>("criacao"));
            colunminicioLocaAtiva.setCellValueFactory(new PropertyValueFactory<>("inicio"));
            colunmfinalLocaAtiva.setCellValueFactory(new PropertyValueFactory<>("fim"));
            colunmvalorLocaAtiva.setCellValueFactory(new PropertyValueFactory<>("valor"));


            textStatus.setVisible(true);
            textStatus.setText("Consulta realizada");
        } catch (SQLException e) {
            textStatus.setVisible(true);
            textStatus.setText("Ocorreu um erro: " + e);
        }
    }
    
    @FXML
    public void preencherTabelaLocacaoI(Connection connection, TableView<LocacaoI> tabela) {
        List<LocacaoI> locacoesinativas = new ArrayList<>();

        String sql = "select * from VLocacaoHistorico";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                textStatus.setVisible(true);
                textStatus.setText("Realizando consulta...");
                int id = resultSet.getInt("idLocacao");
                String nome = resultSet.getString("NomeCompleto");
                String tipo = resultSet.getString("tipoSeguro");
                String modelo = resultSet.getString("modeloCarro");
                String criacao = resultSet.getString("dataCriacao");
                String inicio = resultSet.getString("dataInicio");
                String fim = resultSet.getString("dataFim");
                String valor = resultSet.getString("valorTotal");

                LocacaoI locacaoinativa = new LocacaoI(id, nome, tipo, modelo, criacao, inicio, fim, valor);
                locacoesinativas.add(locacaoinativa);
            }

            tabela.setItems(FXCollections.observableArrayList(locacoesinativas));
            colunmidLocaInativa.setCellValueFactory(new PropertyValueFactory<>("id"));
            colunmnomeLocaInativa.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunmseguroLocaInativa.setCellValueFactory(new PropertyValueFactory<>("tipo"));
            colunmcarroLocaInativa.setCellValueFactory(new PropertyValueFactory<>("modelo"));
            colunmcriacaoLocaInativa.setCellValueFactory(new PropertyValueFactory<>("criacao"));
            colunminicioLocaInativa.setCellValueFactory(new PropertyValueFactory<>("inicio"));
            colunmfinalLocaInativa.setCellValueFactory(new PropertyValueFactory<>("fim"));
            colunmvalorLocaInativa.setCellValueFactory(new PropertyValueFactory<>("valor"));


            textStatus.setVisible(true);
            textStatus.setText("Consulta realizada");
        } catch (SQLException e) {
            textStatus.setVisible(true);
            textStatus.setText("Ocorreu um erro: " + e);
        }
    }

}
