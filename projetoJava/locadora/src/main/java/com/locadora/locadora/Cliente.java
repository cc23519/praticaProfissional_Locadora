/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locadora.locadora;

/**
 *
 * @author u23519
 */
public class Cliente {
    int id;
    String nome, cpf, tipo, telefone, endereco1, endereco2;

    public Cliente(int id, String nome, String cpf, String tipo, String telefone, String endereco1, String endereco2) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.tipo = tipo;
        this.telefone = telefone;
        this.endereco1 = endereco1;
        this.endereco2 = endereco2;
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

    public String getTipo() {
        return tipo;
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

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
