package com.brq.runthebank.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor

public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String agencia;

    @ManyToOne
    private Cliente cliente;

    private BigDecimal saldo;
    private boolean ativa;

    public Conta(String agencia, Cliente cliente, BigDecimal saldo, boolean ativa) {
        this.agencia = agencia;
        this.cliente = cliente;
        this.saldo = saldo;
        this.ativa = ativa;
    }

    public void debitar(BigDecimal valor) {
        this.saldo = this.saldo.subtract(valor);
    }

    public void creditar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }
}

