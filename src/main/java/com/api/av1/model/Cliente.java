package com.api.av1.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @Column(length = 14)
    private String cpf;

    @Column(name = "nome_comprador", length = 100)
    private String nomeComprador;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String telefone;

    public Cliente() {
    }

    public Cliente(String cpf, String nomeComprador, String email, String telefone) {
        this.cpf = cpf;
        this.nomeComprador = nomeComprador;
        this.email = email;
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
