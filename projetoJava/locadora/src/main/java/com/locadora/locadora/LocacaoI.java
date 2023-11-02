package com.locadora.locadora;

public class LocacaoI {
    private int id;
    private String nome;
    private String tipo;
    private String modelo;
    private String criacao;
    private String inicio;
    private String fim;
    private String valor;

    public LocacaoI(int id, String nome, String tipo, String modelo, String criacao, String inicio, String fim, String valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.modelo = modelo;
        this.criacao = criacao;
        this.inicio = inicio;
        this.fim = fim;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCriacao() {
        return criacao;
    }

    public String getInicio() {
        return inicio;
    }

    public String getFim() {
        return fim;
    }

    public String getValor() {
        return valor;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setCriacao(String criacao) {
        this.criacao = criacao;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
  
}
