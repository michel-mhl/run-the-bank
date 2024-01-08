package com.brq.runthebank.repository;

import com.brq.runthebank.domain.Cliente;
import com.brq.runthebank.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<List<Conta>> findByAgencia(String agencia);
    @Query("SELECT c FROM Conta c WHERE c.agencia = :agencia")
    Optional<List<Conta>> findByAgencias(@Param("agencia") String agencia);

    Optional<Conta> findByAgenciaAndCliente(String agencia, Cliente cliente);

    Optional<Conta> findByAgenciaAndClienteId(String agencia, Long clienteId);
}
