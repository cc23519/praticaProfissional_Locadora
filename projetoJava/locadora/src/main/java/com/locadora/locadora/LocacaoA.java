package com.locadora.locadora;

public class LocacaoA {
    private int id;
    private String nome;
    private String tipo;
    private String modelo;
    private String dataCriacao;
    private String dataInicio;
    private String dataFim;
    private String valorTotal;

    public LocacaoA(int id, String nome, String tipo, String modelo, String dataCriacao, String dataInicio, String dataFim, String valorTotal) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.modelo = modelo;
        this.dataCriacao = dataCriacao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorTotal = valorTotal;
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

    public String getDataCriacao() {
        return dataCriacao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public String getValorTotal() {
        return valorTotal;
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

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

}
