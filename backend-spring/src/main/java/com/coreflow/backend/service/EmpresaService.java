package com.coreflow.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coreflow.backend.model.Empresa;
import com.coreflow.backend.repository.EmpresaRepository;

@Service
public class EmpresaService {
    
    private EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<Empresa> listar() {
        return empresaRepository.findAll();
    }

    public Empresa buscarPorId(Long id) {
        return empresaRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa não encontrada."));
    }

    public Empresa cadastrar(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public Empresa atualizar(Long id, Empresa dadosAtualizados) {
        Empresa empresa = buscarPorId(id);

        empresa.setRazaoSocial(dadosAtualizados.getRazaoSocial());
        empresa.setNomeFantasia(dadosAtualizados.getNomeFantasia());
        empresa.setCnpj(dadosAtualizados.getCnpj());
        empresa.setEmail(dadosAtualizados.getEmail());
        empresa.setTelefone(dadosAtualizados.getTelefone());

        return empresaRepository.save(empresa);
    }

    public void deletar(Long id) {
        Empresa empresa = buscarPorId(id);

        empresaRepository.delete(empresa);
    }

}
