package com.api.av1.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compras")
@Getter @Setter @NoArgsConstructor
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Long idCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku")
    private Produto produto;

    @Column(name = "quantidade_comprar")
    private Integer quantidadeComprar;

    @Column(length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'Pendente'")
    private String status = "Pendente";

    @Column(name = "data_registro", updatable = false)
    private LocalDateTime dataRegistro = LocalDateTime.now();

    public Compra(Produto produto, Integer quantidadeComprar) {
        this.produto = produto;
        this.quantidadeComprar = quantidadeComprar;
        this.status = "Pendente";
        this.dataRegistro = LocalDateTime.now();
    }

    public void setStatus(String atendida) {
    }
}