package com.brq.runthebank.repository;

import com.brq.runthebank.domain.Cliente;
import com.brq.runthebank.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
}
