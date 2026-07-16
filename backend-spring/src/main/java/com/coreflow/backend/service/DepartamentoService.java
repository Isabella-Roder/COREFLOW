package com.coreflow.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coreflow.backend.model.Departamento;
import com.coreflow.backend.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
    
    private DepartamentoRepository departamentoRepository;

    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public List<Departamento> listar() {
        return departamentoRepository.findAll();
    }

    public Departamento buscarPorId(Long id) {
        return departamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Departamento não encontrado."));
    }

    public Departamento cadastrar(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public Departamento atualizar(Long id, Departamento dadosAtualizados) {
        Departamento departamento = buscarPorId(id);

        departamento.setNome(dadosAtualizados.getNome());
        departamento.setDescricao(dadosAtualizados.getDescricao());
        departamento.setAtivo(dadosAtualizados.getAtivo());
        departamento.setEmpresa(dadosAtualizados.getEmpresa());

        return departamentoRepository.save(departamento);
    }

    public void deletar(Long id) {
        Departamento departamento = buscarPorId(id);

        departamentoRepository.delete(departamento);
    }

    public List<Departamento> listarPorEmpresa(Long empresaId) {
        return departamentoRepository.findByEmpresaId(empresaId);
    }

}
