package com.mercado.mercado_medieval.dto;

import org.antlr.v4.runtime.misc.NotNull;

import com.mercado.mercado_medieval.model.enums.ClassePersonagem;

import jakarta.validation.constraints.*;

public class PersonagemDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private ClassePersonagem classe;

    @Min(1)
    @Max(99)
    private int nivel;

    @Min(0)
    private double moedas;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ClassePersonagem getClasse() {
        return classe;
    }

    public void setClasse(ClassePersonagem classe) {
        this.classe = classe;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public double getMoedas() {
        return moedas;
    }

    public void setMoedas(double moedas) {
        this.moedas = moedas;
    }
}
