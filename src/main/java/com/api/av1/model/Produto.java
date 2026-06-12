package com.api.av1.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produtos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Produto {

    @Id
    @Column(length = 50)
    private String sku;

    @Column(name = "nome_produto", length = 200)
    private String nomeProduto;

    @Column(name = "estoque_atual", columnDefinition = "INT DEFAULT 0")
    private Integer estoqueAtual = 0;

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

    public String getSku() {
        return sku;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Integer getEstoqueAtual() {
        return estoqueAtual;
    }
}
