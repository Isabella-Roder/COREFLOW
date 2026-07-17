package com.coreflow.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coreflow.backend.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    
    List<Fornecedor> findByEmpresaId(Long empresaId);

}
