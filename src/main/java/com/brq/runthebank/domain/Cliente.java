package com.brq.runthebank.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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


    public Cliente(String document, String name, String adddress, String password) {
        this.document = document;
        this.name = name;
        this.address = adddress;
        this.password = password;
    }
}

