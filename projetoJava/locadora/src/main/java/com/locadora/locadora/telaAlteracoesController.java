package com.locadora.locadora;

import com.locadora.locadora.ClassesAlterações.alterarCarros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class telaAlteracoesController {
    @FXML
    private Button buttonClientes;

    @FXML
    private Button buttonCarro;

    @FXML
    private Button buttonSeguros;
    
    @FXML
    private Button buttonAlterarSelecionado;
        
    @FXML
    private TabPane tabAlteracao;
  
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
    private Tab tabCliente;
    
    @FXML
    private Tab tabCarro;
    
    @FXML
    private Tab tabSeguro;
    
    @FXML
    private Tab tabAtivas;
    
    Connection connection = criarConexaoBanco.criarConexaoBancoDados();
    
    String loginuser; 
    
    @FXML
    public void initialize(){

        buttonAlterarSelecionado.setOnAction(event ->{
            alterarSelecionado();
        });
               
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
        } else {
            textStatus.setVisible(true);
            textStatus.setText("Não foi possível conectar ao banco de dados.");
        }
    }
    
    @FXML
    public void ativarTab(Tab tab){
        tabAlteracao.getSelectionModel().select(tab);
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
    public void alterarSelecionado() {
        Object linhaSelecionada = null;
        alterarCarros alterarCarro = new alterarCarros();

        if (tabAlteracao.getSelectionModel().getSelectedItem() == tabCliente) {
            linhaSelecionada = tableCliente.getSelectionModel().getSelectedItem();
        } else if (tabAlteracao.getSelectionModel().getSelectedItem() == tabCarro) {
            linhaSelecionada = tableCarro.getSelectionModel().getSelectedItem();
        } else if (tabAlteracao.getSelectionModel().getSelectedItem() == tabSeguro) {
            linhaSelecionada = tableSeguros.getSelectionModel().getSelectedItem();
        }

        if (linhaSelecionada != null) {
            if (linhaSelecionada instanceof Carro) {
                loginuser = usuario.getUsername();
                
                Carro carroSelecionado = (Carro) linhaSelecionada;
                Carro dadoscarro = new Carro(
                    carroSelecionado.getId(),
                    carroSelecionado.getModelo(),
                    carroSelecionado.getPlaca(),
                    carroSelecionado.getAno(),
                    carroSelecionado.getChassi(),
                    carroSelecionado.getValor()
                );
                
                abrirTelas abrir = new abrirTelas();
                abrir.abrirTelaComDadosCarro("telaCarro.fxml","Voyage - Alterar carro "+carroSelecionado.getId(), dadoscarro);

                switch (resultado) {
                    case 0:
                        textStatus.setVisible(true);
                        textStatus.setText("Você não tem acesso de moderador para realizar exclusão de carros.");
                        break;
                    case 3:
                        textStatus.setVisible(true);
                        textStatus.setText("Não foi permitido excluir esse carro. Motivo: Existe uma locação ativa utilizando esse dado. Excluir locação para prosseguir.");
                        break;
                    case 4:
                        textStatus.setVisible(true);
                        textStatus.setText("Exclusão realizada com sucesso.");
                        break;
                    default:
                        textStatus.setVisible(true);
                        textStatus.setText("Ocorreu um erro.");
                        break;
                }
            } else if (linhaSelecionada instanceof Seguro) {
                loginuser = usuario.getUsername();
                Seguro seguroSelecionado = (Seguro) linhaSelecionada;
                Seguro dadosseguro = new Seguro(
                    seguroSelecionado.getId(),
                    seguroSelecionado.getTipo(),
                    seguroSelecionado.getPreco(),
                    seguroSelecionado.getDescricao()
                );
               
                abrirTelas abrir = new abrirTelas();
                abrir.abrirTelaComDadosSeguros("telaSeguro.fxml","Voyage - Alterar seguro "+seguroSelecionado.getId(), dadosseguro);
                
                switch (resultado) {
                    case 0:
                        textStatus.setVisible(true);
                        textStatus.setText("Você não tem acesso de moderador para realizar exclusão de seguros.");
                        break;
                    case 3:
                        textStatus.setVisible(true);
                        textStatus.setText("Não foi permitido excluir esse seguro. Motivo: Existe uma locação ativa utilizando esse dado. Excluir locação para prosseguir.");
                        break;
                    case 4:
                        textStatus.setVisible(true);
                        textStatus.setText("Exclusão realizada com sucesso.");
                        break;
                    default:
                        textStatus.setVisible(true);
                        textStatus.setText("Ocorreu um erro.");
                        break;
                }
            } else if (linhaSelecionada instanceof Cliente) {
                loginuser = usuario.getUsername();
                Cliente clienteSelecionado = (Cliente) linhaSelecionada;
                
                Cliente dadoscliente = new Cliente(
                    clienteSelecionado.getId(),
                    clienteSelecionado.getNome(),
                    clienteSelecionado.getCpf(),
                    clienteSelecionado.getTipoTelefone(),
                    clienteSelecionado.getTelefone(),
                    clienteSelecionado.getRua(),
                    clienteSelecionado.getNumero(),
                    clienteSelecionado.getBairro(),
                    clienteSelecionado.getCidade(),
                    clienteSelecionado.getEstado(),
                    clienteSelecionado.getCep(),
                    clienteSelecionado.getComplemento()
                );
                
                abrirTelas abrir = new abrirTelas();
                abrir.abrirTelaComDadosCliente("telaCliente.fxml","Voyage - Alterar Cliente "+clienteSelecionado.getId(), dadoscliente);


                switch (resultado) {
                    case 0:
                        textStatus.setVisible(true);
                        textStatus.setText("Você não tem acesso de moderador para realizar exclusão de clientes.");
                        break;
                    case 3:
                        textStatus.setVisible(true);
                        textStatus.setText("Não foi permitido excluir esse cliente. Motivo: Existe uma locação ativa utilizando esse dado. Excluir locação para prosseguir.");
                        break;
                    case 4:
                        textStatus.setVisible(true);
                        textStatus.setText("Exclusão realizada com sucesso.");
                        break;
                    default:
                        textStatus.setVisible(true);
                        textStatus.setText("Ocorreu um erro.");
                        break;
                }
            }
        } else {
            textStatus.setVisible(true);
            textStatus.setText("Não há linhas selecionadas.");
        }
    }
  
}
