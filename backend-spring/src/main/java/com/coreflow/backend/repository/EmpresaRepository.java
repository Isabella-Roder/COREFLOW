package com.coreflow.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coreflow.backend.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    

}
