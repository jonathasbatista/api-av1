package com.api.av1.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentos")
@Getter @Setter @NoArgsConstructor
public class Movimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimentacao")
    private Long idMovimentacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku")
    private Produto produto;

    @Column(name = "order_id", length = 50)
    private String orderId;

    @Column(name = "tipo_movimento", length = 10)
    private String tipoMovimento;

    private Integer quantidade;

    @Column(name = "saldo_apos_movimentacao")
    private Integer saldoAposMovimentacao;

    @Column(name = "data_movimentacao", updatable = false)
    private LocalDateTime dataMovimentacao = LocalDateTime.now();

    public Movimento(Produto produto, String orderId, String tipoMovimento, Integer quantidade, Integer saldoAposMovimentacao) {
        this.produto = produto;
        this.orderId = orderId;
        this.tipoMovimento = tipoMovimento;
        this.quantidade = quantidade;
        this.saldoAposMovimentacao = saldoAposMovimentacao;
        this.dataMovimentacao = LocalDateTime.now();
    }
}
