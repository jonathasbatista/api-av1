package com.api.av1.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter @Setter @NoArgsConstructor
public class Pedido {

    @Id
    @Column(name = "order_id", length = 50)
    private String orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cpf_cliente")
    private Cliente cliente;

    @Column(name = "data_compra")
    private LocalDate dataCompra;

    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(length = 30, columnDefinition = "VARCHAR(30) DEFAULT 'Pendente'")
    private String status = "Pendente";

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> itens;

    public void marcarComoAtendido() {
        this.status = "Atendido";
    }

    public void marcarAguardandoEstoque() {
        this.status = "Aguardando Estoque";
    }

    public String getOrderId() {
        return orderId;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}
