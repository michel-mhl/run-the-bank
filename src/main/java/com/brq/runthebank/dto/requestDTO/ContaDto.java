package com.brq.runthebank.dto.requestDTO;

import com.brq.runthebank.domain.Cliente;
import com.brq.runthebank.domain.Conta;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ContaDto {

    private String agencia;
    private Cliente cliente;
    private BigDecimal saldo;
    private boolean ativa;

    public Conta toModel(){
        return new Conta(agencia, cliente, saldo,ativa);
    }
}
