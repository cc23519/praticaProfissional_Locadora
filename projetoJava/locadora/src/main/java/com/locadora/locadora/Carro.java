package com.locadora.locadora;

public class Carro {
    private int id;
    private String modelo;
    private String placa;
    private String chassi;
    private String valor;
    private String status;
    private String ano;
    private String preco;

    public Carro(int id, String modelo, String placa, String chassi, String valor, String status) {
        this.id = id;
        this.modelo = modelo;
        this.placa = placa;
        this.chassi = chassi;
        this.valor = valor;
        this.status = status;  
    }

    public Carro(int id, String modelo, String placa, String chassi, String valor, String status, String ano, String preco) {
        this.id = id;
        this.modelo = modelo;
        this.placa = placa;
        this.chassi = chassi;
        this.valor = valor;
        this.status = status;
        this.ano = ano;
        this.preco = preco;
    }

    public String getPreco() {
        return preco;
    }
    
    
    public int getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public String getAno() {
        return ano;
    }
    
    
    public String getPlaca() {
        return placa;
    }

    public String getChassi() {
        return chassi;
    }

    public String getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
