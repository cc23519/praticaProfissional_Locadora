package com.locadora.locadora.controladoresAlterações;

import com.locadora.locadora.ClassesAlterações.alterarClientes;
import com.locadora.locadora.Cliente;
import com.locadora.locadora.usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class telaClienteController {
    @FXML
    public TextField textfieldNome;
    
    @FXML
    public TextField textfieldSobrenome;
            
    @FXML
    public TextField textfieldDDDTelefone;
    
    @FXML
    public TextField textfieldTelefone;
            
    @FXML
    public TextField textfieldRua;
            
    @FXML
    public TextField textfieldNumero;
            
    @FXML
    public TextField textfieldBairro;
            
    @FXML
    public TextField textfieldCidade;
            
    @FXML
    public TextField textfieldCep;
    
    @FXML
    public TextField textfieldComplemento;
    
    @FXML
    public ComboBox comboboxTipo, comboboxEstado;
    
    @FXML
    public Button buttonFinalizar;
    
    @FXML
    public Button buttonSair;
    
    @FXML
    public Label statusCliente;
    
    
    private Integer id;
    private String loginuser, nome, sobrenome, cpf, tipo, telefone, rua, numero, bairro, cidade, estado, cep, complemento;
    
    @FXML
    public void initialize() {
        buttonSair.setOnAction(event -> {
            Stage stage = (Stage) buttonSair.getScene().getWindow();
            stage.close();
        });
    }

    @FXML
    public void inicializarDadosCliente(Object... dadosCliente) {
        if (dadosCliente.length > 0 && dadosCliente[0] instanceof Cliente) {
            loginuser = usuario.getUsername();
            Cliente cliente = (Cliente) dadosCliente[0];
            id = cliente.getId();
            nome = cliente.getNome();
            sobrenome = cliente.getSobrenome();
            cpf = cliente.getCpf();
            tipo = cliente.getTipoTelefone();
            telefone = cliente.getTelefone();
            rua = cliente.getRua();
            numero = cliente.getNumero();
            bairro = cliente.getBairro();
            cidade = cliente.getCidade();
            estado = cliente.getEstado();
            cep = cliente.getCep();
            complemento = cliente.getComplemento();
        }
    }  
    
    public void inserirDados(String nome, String sobrenome, String ddd, String telefone, String rua, String numero, 
    String bairro, String cidade, String cep, String complemento,
    String tipo, String estado){
        textfieldNome.setText(nome);
        textfieldSobrenome.setText(sobrenome);
        textfieldDDDTelefone.setText(ddd);
        textfieldTelefone.setText(telefone);
        textfieldRua.setText(rua);
        textfieldNumero.setText(numero);
        textfieldBairro.setText(bairro);
        textfieldCidade.setText(cidade);
        textfieldCep.setText(cep);
        textfieldComplemento.setText(complemento);
        comboboxTipo.setValue(tipo);
        comboboxEstado.setValue(estado);
    }
    
    public void AlterarCliente(){
        loginuser = usuario.getUsername();
        String nome = textfieldNome.getText();
        String sobrenome = textfieldSobrenome.getText();
        String DDD = textfieldDDDTelefone.getText();
        String telefone = textfieldTelefone.getText();
        String rua = textfieldRua.getText();
        String numero = textfieldNumero.getText();
        String bairro = textfieldBairro.getText();
        String cidade = textfieldCidade.getText();
        String cep = textfieldCep.getText();
        String complemento = textfieldComplemento.getText();
        String tipo = (String) comboboxTipo.getValue();
        String estado = (String) comboboxEstado.getValue();
        
        alterarClientes alterar = new alterarClientes();
        
        int resultado = alterar.alterarCliente(loginuser, nome, sobrenome, tipo, DDD, telefone, rua, numero,bairro, cidade, estado, cep, complemento, id);
        
                switch (resultado) {
            case 0:
                statusCliente.setVisible(true);
                statusCliente.setText("Você não tem acesso de moderador para realizar alteração de Cliente.");
                break;
            case 4:
                statusCliente.setVisible(true);
                statusCliente.setText("Exclusão realizada com sucesso.");
                break;
            default:
                statusCliente.setVisible(true);
                statusCliente.setText("Ocorreu um erro.");
                break;
        }
    }
}
