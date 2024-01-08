package com.brq.runthebank.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Cliente {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String document;

    private String name;
    private String address;
    private String password;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Conta> contas;


    public Cliente(String document, String name, String address, String password) {
        this.document = document;
        this.name = name;
        this.address = address;
        this.password = password;
    }



    public void adicionarConta(Conta conta) {
        this.contas.add(conta);
        conta.setCliente(this);
    }

}

