package com.coreflow.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coreflow.backend.enums.StatusContaFinanceira;
import com.coreflow.backend.enums.TipoContaFinanceira;
import com.coreflow.backend.model.ContaFinanceira;

public interface ContaFinanceiraRepository extends JpaRepository<ContaFinanceira, Long> {
    
    List<ContaFinanceira> findByEmpresaId(Long empresaId);

    List<ContaFinanceira> findByEmpresaIdAndTipo(Long empresaId, TipoContaFinanceira tipo);

    List<ContaFinanceira> findByEmpresaIdAndStatus(Long empresaId, StatusContaFinanceira status);

    List<ContaFinanceira> findByEmpresaIdAndTipoAndStatus(Long empresaId, TipoContaFinanceira tipo, StatusContaFinanceira status);

}
