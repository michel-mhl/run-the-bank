package com.brq.runthebank.dto.requestDTO;

import com.brq.runthebank.domain.Cliente;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDTO {
    private String document;
    private String name;
    private String address;
    private String password;


    public Cliente toModel(){
        return new Cliente(document,name, address, password);
    }
}
