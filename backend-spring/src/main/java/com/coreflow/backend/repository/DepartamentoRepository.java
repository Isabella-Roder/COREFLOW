package com.coreflow.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coreflow.backend.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    
    List<Departamento> findByEmpresaId(Long empresaId);

}
