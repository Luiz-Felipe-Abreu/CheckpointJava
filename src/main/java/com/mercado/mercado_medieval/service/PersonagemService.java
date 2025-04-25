package com.mercado.mercado_medieval.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercado.mercado_medieval.model.enums.TipoItem;
import com.mercado.mercado_medieval.model.enums.RaridadeItem;
import com.mercado.mercado_medieval.model.enums.ClassePersonagem;
import com.mercado.mercado_medieval.model.Personagem;
import com.mercado.mercado_medieval.repository.PersonagemRepository;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    public Personagem salvar(Personagem personagem) {
        // Regras de validação, se quiser
        return personagemRepository.save(personagem);
    }

    public List<Personagem> buscarTodos() {
        return personagemRepository.findAll();
    }

    public Personagem buscarPorId(Long id) {
        Optional<Personagem> personagem = personagemRepository.findById(id);
        return personagem.orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
    }

    public Personagem atualizar(Long id, Personagem novosDados) {
        Personagem personagem = buscarPorId(id);
        personagem.setNome(novosDados.getNome());
        personagem.setClasse(novosDados.getClasse());
        personagem.setNivel(novosDados.getNivel());
        personagem.setMoedas(novosDados.getMoedas());
        return personagemRepository.save(personagem);
    }

    public void deletar(Long id) {
        personagemRepository.deleteById(id);
    }

    public List<Personagem> buscarPorNome(String nome) {
        return personagemRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Personagem> buscarPorClasse(Classe classe) {
        return personagemRepository.findByClasse(classe);
    }
}
