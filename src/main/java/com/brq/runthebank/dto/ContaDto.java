package com.brq.runthebank.dto;

import com.brq.runthebank.domain.Cliente;
import com.brq.runthebank.domain.Conta;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ContaDto {

    private String agencia;
    private String document;
    private BigDecimal saldo;
    private boolean ativa;

    public Conta toModel(Cliente cliente){
        return new Conta(agencia, cliente, saldo,ativa);
    }

    public static List<ContaDto> fromModel(List<Conta> contas) {
        return contas.stream()
                .map(ContaDto::fromModel)
                .collect(Collectors.toList());
    }

    public static ContaDto fromModel(Conta conta) {
        ContaDto dto = new ContaDto();
        dto.setAgencia(conta.getAgencia());
        dto.setDocument(conta.getCliente().getDocument());
        dto.setSaldo(conta.getSaldo());
        dto.setAtiva(conta.isAtiva());

        return dto;
    }
}
