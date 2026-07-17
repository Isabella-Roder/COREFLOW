package com.coreflow.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coreflow.backend.model.ItemVenda;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
    
    List<ItemVenda> findByVendaId(Long vendaId);

}
