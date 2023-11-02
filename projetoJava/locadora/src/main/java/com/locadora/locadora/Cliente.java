package com.locadora.locadora;

public class Cliente {
        private int id;
        private String nome;
        private String cpf;
        private String tipoTelefone;
        private String telefone;
        private String endereco1;
        private String endereco2;

        public Cliente(int id, String nome, String cpf, String tipoTelefone, String telefone, String endereco1, String endereco2) {
            this.id = id;
            this.nome = nome;
            this.cpf = cpf;
            this.tipoTelefone = tipoTelefone;
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
