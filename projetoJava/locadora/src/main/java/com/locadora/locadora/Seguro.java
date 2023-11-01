
package com.locadora.locadora;

public class Seguro {
    private int id;
    private String tipo;
    private String preco;
    private String descricao;

    public Seguro(int id, String tipo, String preco, String descricao) {
        this.id = id;
        this.tipo = tipo;
        this.preco = preco;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
