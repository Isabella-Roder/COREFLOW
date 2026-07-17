package com.coreflow.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coreflow.backend.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    
    List<Estoque> findByEmpresaId(Long empresaId);

    Optional<Estoque> findByProdutoServicoId(Long produtoServicoId);

    List<Estoque> findByEmpresaIdAndQuantidadeAtualLessThanEqual(Long empresaId, Integer estoqueMinimo);

    @Query("SELECT e FROM Estoque e WHERE e.empresa.id = :empresaId AND e.quantidadeAtual <= e.estoqueMinimo")
    List<Estoque> buscarEstoqueBaixoPorEmpresa(@Param("empresaId") Long empresaId);

}
