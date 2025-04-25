package com.mercado.mercado_medieval.repository;

import com.mercado.mercado_medieval.model.Personagem;
import com.mercado.mercado_medieval.model.enums.ClassePersonagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    Page<Personagem> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    Page<Personagem> findByClasse(ClassePersonagem classe, Pageable pageable);
}
