package com.api.av1.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @Column(length = 50)
    private String sku;

    @Column(name = "nome_produto", length = 200)
    private String nomeProduto;

    @Column(name = "estoque_atual", columnDefinition = "INT DEFAULT 0")
    private Integer estoqueAtual = 0;

    public Produto() {
    }

    public Produto(String sku, String nomeProduto, Integer estoqueAtual) {
        this.sku = sku;
        this.nomeProduto = nomeProduto;
        this.estoqueAtual = estoqueAtual;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(Integer estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public void adicionarEstoque(Integer quantidade) {
        this.estoqueAtual += quantidade;
    }

    public void removerEstoque(Integer quantidade) {
        if (!temEstoqueSuficiente(quantidade)) {
            throw new RuntimeException("Estoque insuficiente para o SKU: " + this.sku);
        }
        this.estoqueAtual -= quantidade;
    }

    public boolean temEstoqueSuficiente(Integer quantidadeNecessaria) {
        return this.estoqueAtual >= quantidadeNecessaria;
    }
}
