package com.brq.runthebank.sevices;

import com.brq.runthebank.domain.Cliente;
import com.brq.runthebank.repository.ClienteRepository;
import com.brq.runthebank.services.ClienteService;
import com.brq.runthebank.services.exceptions.DocumentDuplicationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void testCadastrarClienteWithUniqueDocument() {

        Cliente cliente = new Cliente("123456789", "John Doe", "Address", "password");

        when(clienteRepository.findByDocument(cliente.getDocument())).thenReturn(Optional.empty());
        when(clienteRepository.save(cliente)).thenReturn(cliente);


        Cliente result = clienteService.cadastrarCliente(cliente);


        assertNotNull(result);
        assertEquals(cliente, result);


        verify(clienteRepository, times(1)).findByDocument(cliente.getDocument());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testCadastrarClienteWithDuplicateDocument() {

        Cliente cliente = new Cliente("123456789", "John Doe", "Address", "password");

        when(clienteRepository.findByDocument(cliente.getDocument())).thenReturn(Optional.of(cliente));


        assertThrows(DocumentDuplicationException.class, () -> clienteService.cadastrarCliente(cliente));


        verify(clienteRepository, times(1)).findByDocument(cliente.getDocument());

        verify(clienteRepository, never()).save(cliente);
    }


}

