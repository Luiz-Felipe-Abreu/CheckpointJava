package com.mercado.mercado_medieval.service;

import com.mercado.mercado_medieval.model.Personagem;
import com.mercado.mercado_medieval.model.enums.ClassePersonagem;
import com.mercado.mercado_medieval.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    public Personagem criar(Personagem personagem) {
        return personagemRepository.save(personagem);
    }

    public Page<Personagem> listarTodos(Pageable pageable) {
        return personagemRepository.findAll(pageable);
    }

    public Optional<Personagem> buscarPorId(Long id) {
        return personagemRepository.findById(id);
    }

    public Optional<Personagem> atualizar(Long id, Personagem personagem) {
        return personagemRepository.findById(id).map(p -> {
            p.setNome(personagem.getNome());
            p.setClasse(personagem.getClasse());
            p.setNivel(personagem.getNivel());
            p.setMoedas((int)personagem.getMoedas());
            return personagemRepository.save(p);
        });
    }

    public boolean deletar(Long id) {
        if (personagemRepository.existsById(id)) {
            personagemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<Personagem> buscarPorNome(String nome, Pageable pageable) {
        return personagemRepository.findByNomeContainingIgnoreCase(nome, pageable);
    }

    public Page<Personagem> buscarPorClasse(ClassePersonagem classe, Pageable pageable) {
        return personagemRepository.findByClasse(classe, pageable);
    }
} 
