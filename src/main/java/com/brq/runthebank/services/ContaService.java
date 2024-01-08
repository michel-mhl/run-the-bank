package com.brq.runthebank.services;

import com.brq.runthebank.domain.Conta;
import com.brq.runthebank.repository.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Transactional
    public Conta cadastrar(Conta conta) {
        return contaRepository.save(conta);
    }
}

