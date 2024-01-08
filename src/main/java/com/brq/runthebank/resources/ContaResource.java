package com.brq.runthebank.resources;

import com.brq.runthebank.domain.Conta;
import com.brq.runthebank.dto.requestDTO.ContaDto;
import com.brq.runthebank.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
public class ContaResource {
    @Autowired
    private ContaService contaService;


    @GetMapping
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Conta> cadastrar(@RequestBody ContaDto contaDto){
        Conta conta = contaDto.toModel();
        contaService.cadastrar(conta);
      return  ResponseEntity.ok().body(conta);
    }
}

