package com.mercado.mercado_medieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercado.mercado_medieval.model.Personagem;
import com.mercado.mercado_medieval.service.PersonagemService;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @PostMapping
    public Personagem criarPersonagem(@RequestBody Personagem personagem) {
        return personagemService.salvar(personagem);
    }

    @GetMapping
    public List<Personagem> listarTodos() {
        return personagemService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Personagem buscarPorId(@PathVariable Long id) {
        return personagemService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Personagem atualizar(@PathVariable Long id, @RequestBody Personagem personagem) {
        return personagemService.atualizar(id, personagem);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        personagemService.deletar(id);
    }

    @GetMapping("/buscar/nome")
    public List<Personagem> buscarPorNome(@RequestParam String nome) {
        return personagemService.buscarPorNome(nome);
    }

    @GetMapping("/buscar/classe")
    public List<Personagem> buscarPorClasse(@RequestParam Classe classe) {
        return personagemService.buscarPorClasse(classe);
    }
}
