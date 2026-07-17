package com.coreflow.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coreflow.backend.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    
    List<Venda> findByEmpresaId(Long empresaId);

    List<Venda> findByClienteId(Long clienteId);

}
