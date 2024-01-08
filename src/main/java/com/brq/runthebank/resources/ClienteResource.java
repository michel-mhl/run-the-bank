package com.brq.runthebank.resources;

import com.brq.runthebank.domain.Cliente;
import com.brq.runthebank.services.ClienteService;
import com.brq.runthebank.services.exceptions.DocumentDuplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

  //  @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) {
        try {
            Cliente novoCliente = clienteService.cadastrarCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
        } catch (DocumentDuplicationException e) {
            String mensagemErro = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", mensagemErro));
        }
    }

    @GetMapping(params = "document")
    public ResponseEntity buscarCliente(@RequestParam String document) {
        Optional<Cliente> cliente = clienteService.getByCliente(document);
        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }
    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.getAllClientes();
        return ResponseEntity.ok().body(clientes);
    }

}

