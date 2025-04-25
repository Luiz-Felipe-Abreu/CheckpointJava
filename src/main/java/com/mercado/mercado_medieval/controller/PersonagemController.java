package com.mercado.mercado_medieval.controller;

import com.mercado.mercado_medieval.model.Personagem;
import com.mercado.mercado_medieval.model.enums.ClassePersonagem;
import com.mercado.mercado_medieval.service.PersonagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @PostMapping
    public ResponseEntity<Personagem> criar(@RequestBody @Valid Personagem personagem) {
        Personagem salvo = personagemService.criar(personagem);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<Page<Personagem>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(personagemService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personagem> buscarPorId(@PathVariable Long id) {
        Optional<Personagem> personagem = personagemService.buscarPorId(id);
        return personagem.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personagem> atualizar(@PathVariable Long id, @RequestBody @Valid Personagem personagem) {
        Optional<Personagem> atualizado = personagemService.atualizar(id, personagem);
        return atualizado.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (personagemService.deletar(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar-nome")
    public ResponseEntity<Page<Personagem>> buscarPorNome(@RequestParam String nome, Pageable pageable) {
        return ResponseEntity.ok(personagemService.buscarPorNome(nome, pageable));
    }

    @GetMapping("/buscar-classe")
    public ResponseEntity<Page<Personagem>> buscarPorClasse(@RequestParam ClassePersonagem classe, Pageable pageable) {
        return ResponseEntity.ok(personagemService.buscarPorClasse(classe, pageable));
    }
}
