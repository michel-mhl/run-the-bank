package com.brq.runthebank.services;

import com.brq.runthebank.domain.Cliente;
import com.brq.runthebank.repository.ClienteRepository;
import com.brq.runthebank.services.exceptions.DocumentDuplicationException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Cliente> getByCliente(String documento) {
        return clienteRepository.findByDocument(documento);
    }

    public List<Cliente> getAllClientes() {
      List<Cliente> clientes =  clienteRepository.findAll();
        return clientes;
    }
}

