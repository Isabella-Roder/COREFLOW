package com.coreflow.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coreflow.backend.model.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    
    List<Colaborador> findByEmpresaId(Long empresaId);

}
