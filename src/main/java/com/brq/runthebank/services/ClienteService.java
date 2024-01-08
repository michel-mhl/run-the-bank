package com.brq.runthebank.services;

import com.brq.runthebank.domain.Cliente;
import com.brq.runthebank.repository.ClienteRepository;
import com.brq.runthebank.services.exceptions.DocumentDuplicationException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;


    @Transactional
    public Cliente cadastrarCliente(Cliente cliente) {
        // Verificar se o CPF/CNPJ já está cadastrado
        if (clienteRepository.findByDocument(cliente.getDocument()).isPresent()) {
            throw new DocumentDuplicationException("CPF/CNPJ já cadastrado: " + cliente.getDocument());
        }

        return clienteRepository.save(cliente);
    }
}

