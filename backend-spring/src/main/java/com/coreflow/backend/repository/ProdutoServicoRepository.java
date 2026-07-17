package com.coreflow.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coreflow.backend.model.ProdutoServico;

public interface ProdutoServicoRepository extends JpaRepository<ProdutoServico, Long> {
    
    public List<ProdutoServico> findByEmpresaId(Long empresaId);

}
