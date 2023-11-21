package com.locadora.locadora;

public class Cliente {

    private int id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String ddd;
    private String tipoTelefone;
    private String telefone;
    private String endereco1;
    private String endereco2;

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String complemento;

    public Cliente(int id, String nome, String cpf, String tipoTelefone, String telefone, String endereco1, String endereco2, String telefone2, String bairro1, String cidade1, String estado1, String cep1, String complemento1) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.tipoTelefone = tipoTelefone;
        this.telefone = telefone;
        this.endereco1 = endereco1;
        this.endereco2 = endereco2;
    }

    public Cliente(int id, String nome, String sobrenome, String cpf, String tipoTelefone,String ddd, String telefone, String rua, String numero, String bairro, String cidade, String estado, String cep, String complemento) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.tipoTelefone = tipoTelefone;
        this.ddd = ddd;
        this.telefone = telefone;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.complemento = complemento;
    }

    public Cliente(int id, String nome, String cpf, String tipoTelefone, String telefone, String endereco1, String endereco2) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.tipoTelefone = tipoTelefone;
        this.telefone = telefone;
        this.endereco1 = endereco1;
        this.endereco2 = endereco2;
    }
    
    public String getDdd() {
        return ddd;
    }
    
    

    public String getRua() {
        return rua;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco1() {
        return endereco1;
    }

    public String getEndereco2() {
        return endereco2;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco1(String endereco1) {
        this.endereco1 = endereco1;
    }

    public void setEndereco2(String endereco2) {
        this.endereco2 = endereco2;
    }
}
