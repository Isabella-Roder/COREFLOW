package com.coreflow.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coreflow.backend.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    
    List<Compra> findByEmpresaId(Long empresaId);

    List<Compra> findByFornecedorId(Long fornecedorId);

}
