package com.mercado.mercado_medieval.dto;

import org.antlr.v4.runtime.misc.NotNull;

import com.mercado.mercado_medieval.model.enums.RaridadeItem;
import com.mercado.mercado_medieval.model.enums.TipoItem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ItemDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private TipoItem tipo;

    @NotNull
    private RaridadeItem raridade;

    @Min(0)
    private double preco;

    private Long donoId; // Referência ao personagem dono

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

    public TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
    }

    public RaridadeItem getRaridade() {
        return raridade;
    }

    public void setRaridade(RaridadeItem raridade) {
        this.raridade = raridade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Long getDonoId() {
        return donoId;
    }

    public void setDonoId(Long donoId) {
        this.donoId = donoId;
    }
}
