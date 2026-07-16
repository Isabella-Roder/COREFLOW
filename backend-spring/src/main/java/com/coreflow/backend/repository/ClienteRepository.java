package com.coreflow.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coreflow.backend.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    List<Cliente> findByEmpresaId(Long empresaId);

}
