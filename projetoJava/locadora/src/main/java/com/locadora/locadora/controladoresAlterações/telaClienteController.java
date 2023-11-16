package com.locadora.locadora.controladoresAlterações;

import com.locadora.locadora.Cliente;
import com.locadora.locadora.usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class telaClienteController {
    @FXML
    public TextField textfieldNome;
    
    @FXML
    public TextField textfieldSobrenome;
            
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
    
    
    private Integer id;
    private String loginuser, nome, cpf, tipo, telefone, rua, numero, bairro, cidade, estado, cep, complemento;
    
    @FXML
    public void initialize() {
        
    }

    @FXML
    public void inicializarDadosCliente(Object... dadosCliente) {
        if (dadosCliente.length > 0 && dadosCliente[0] instanceof Cliente) {
            loginuser = usuario.getUsername();
            Cliente cliente = (Cliente) dadosCliente[0];
            id = cliente.getId();
            nome = cliente.getNome();
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
}
