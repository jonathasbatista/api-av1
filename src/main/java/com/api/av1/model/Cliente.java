package com.api.av1.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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
}
