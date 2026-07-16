package com.coreflow.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coreflow.backend.model.Colaborador;
import com.coreflow.backend.repository.ColaboradorRepository;

@Service
public class ColaboradorService {
    
    private ColaboradorRepository colaboradorRepository;

    public ColaboradorService(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    public List<Colaborador> listar() {
        return colaboradorRepository.findAll();
    }

    public Colaborador buscarPorId(Long id) {
        return colaboradorRepository.findById(id).orElseThrow(() -> new RuntimeException("Colaborador não encontrado."));
    }

    public Colaborador cadastrar(Colaborador colaborador) {
        return colaboradorRepository.save(colaborador);
    }

    public Colaborador atualizar(Long id, Colaborador dadosAtualizados) {
        Colaborador colaborador = buscarPorId(id);

        colaborador.setNome(dadosAtualizados.getNome());
        colaborador.setEmail(dadosAtualizados.getEmail());
        colaborador.setSenha(dadosAtualizados.getSenha());
        colaborador.setCargo(dadosAtualizados.getCargo());
        colaborador.setAtivo(dadosAtualizados.getAtivo());
        colaborador.setPerfil(dadosAtualizados.getPerfil());
        colaborador.setEmpresa(dadosAtualizados.getEmpresa());

        return colaboradorRepository.save(colaborador);
    }

    public void deletar(Long id) {
        Colaborador colaborador = buscarPorId(id);

        colaboradorRepository.delete(colaborador);
    }

    public List<Colaborador> listarPorEmpresa(Long empresaId) {
        return colaboradorRepository.findByEmpresaId(empresaId);
    }

}
