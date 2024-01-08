package com.brq.runthebank.resources;

import com.brq.runthebank.domain.Conta;
import com.brq.runthebank.dto.ContaDto;
import com.brq.runthebank.services.ContaService;
import com.brq.runthebank.services.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conta")
public class ContaResource {
    @Autowired
    private ContaService contaService;


    @GetMapping
    public String status(){
        return "ok";
    }

    @PostMapping()
    public ResponseEntity<?> cadastrar(@RequestBody ContaDto contaDto) {
        try {
            Conta conta = contaService.cadastrar(contaDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(conta);
        } catch (DocumentDuplicationException e) {
            String mensagemErro = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", mensagemErro));
        }
    }

    @GetMapping(value= "/{agencia}")
    public ResponseEntity<List<ContaDto>> contasPorAgencia(@PathVariable String agencia) {
        List<ContaDto> contas = contaService.obterContasPorAgencia(agencia);
        return ResponseEntity.ok(contas);
    }
    @PostMapping("/transferir")
    public ResponseEntity<String> transferir(@RequestParam String agenciaOrigem,
                                             @RequestParam Long idContaOrigem,
                                             @RequestParam String senhaOrigem,
                                             @RequestParam String agenciaDestino,
                                             @RequestParam Long idContaDestino,
                                             @RequestParam BigDecimal valor) {
        try {
            contaService.transferir(agenciaOrigem, idContaOrigem, senhaOrigem,
                    agenciaDestino, idContaDestino, valor);
            return ResponseEntity.ok("Transferência realizada com sucesso");
        } catch (ContaNotFoundException | ContaInativaException | SaldoInsuficienteException | SenhaIncorretaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}

