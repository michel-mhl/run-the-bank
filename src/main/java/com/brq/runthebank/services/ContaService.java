package com.brq.runthebank.services;

import com.brq.runthebank.domain.Cliente;
import com.brq.runthebank.domain.Conta;
import com.brq.runthebank.dto.ContaDto;
import com.brq.runthebank.repository.ClienteRepository;
import com.brq.runthebank.repository.ContaRepository;
import com.brq.runthebank.services.exceptions.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Conta cadastrar(ContaDto contaDto) {
        // Verifica a existência do cliente
        Cliente cliente = clienteRepository.findByDocument(contaDto.getDocument())
                .orElseThrow(() -> new ClientNotFoundException("Cliente não encontrado com documento: " + contaDto.getDocument()));

        // Verifica se já existe uma conta com a mesma agência para esse cliente
        contaRepository.findByAgenciaAndClienteId(contaDto.getAgencia(), cliente.getId())
                .ifPresent(c -> {
                    throw new DocumentDuplicationException("Agencia já cadastrada para: " + cliente.getDocument());
                });

        // Cria a conta
        Conta conta = new Conta(contaDto.getAgencia(), cliente, contaDto.getSaldo(), contaDto.isAtiva());

        // Adiciona a conta ao cliente
        cliente.adicionarConta(conta);

        // Salva o cliente (isso também deve salvar a conta devido à cascata)
        clienteRepository.save(cliente);

        return conta;
    }

    public List<ContaDto> obterContasPorAgencia(String agencia) {
        Optional<List<Conta>> contas = contaRepository.findByAgencia(agencia);
        return contas.map(this::mapToContaDtoList).orElse(Collections.emptyList());
    }

    private List<ContaDto> mapToContaDtoList(List<Conta> contas) {
        return contas.stream()
                .map(ContaDto::fromModel)
                .collect(Collectors.toList());
    }

    @Transactional
    public void transferir(String agenciaOrigem,Long idContaOrigem,String senhaOrigem,String agenciaDestino, Long idContaDestino, BigDecimal valor) {
        Conta contaOrigem = contaRepository.findById(idContaOrigem)
                .orElseThrow(() -> new ContaNotFoundException("Conta de origem não encontrada"));

        Conta contaDestino = contaRepository.findById(idContaDestino)
                .orElseThrow(() -> new ContaNotFoundException("Conta de destino não encontrada"));

        if (!contaOrigem.isAtiva() || !contaDestino.isAtiva()) {
            throw new ContaInativaException("Uma ou ambas as contas estão inativas");
        }

        if (!contaOrigem.getCliente().getPassword().equals(senhaOrigem)) {
            throw new SenhaIncorretaException("Senha incorreta para a conta de origem");
        }
        if (contaOrigem.getSaldo().compareTo(valor) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente na conta de origem");
        }

        contaOrigem.debitar(valor);
        contaDestino.creditar(valor);

        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);
    }

}

