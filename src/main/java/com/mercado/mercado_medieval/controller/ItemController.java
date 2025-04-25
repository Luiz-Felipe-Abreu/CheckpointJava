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

import com.mercado.mercado_medieval.model.Item;
import com.mercado.mercado_medieval.service.ItemService;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public Item criarItem(@RequestBody Item item) {
        return itemService.salvar(item);
    }

    @GetMapping
    public List<Item> listarTodos() {
        return itemService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Item buscarPorId(@PathVariable Long id) {
        return itemService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Item atualizar(@PathVariable Long id, @RequestBody Item item) {
        return itemService.atualizar(id, item);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        itemService.deletar(id);
    }

    @GetMapping("/buscar/nome")
    public List<Item> buscarPorNomeParcial(@RequestParam String nome) {
        return itemService.buscarPorNomeParcial(nome);
    }

    @GetMapping("/buscar/tipo")
    public List<Item> buscarPorTipo(@RequestParam Tipo tipo) {
        return itemService.buscarPorTipo(tipo);
    }

    @GetMapping("/buscar/raridade")
    public List<Item> buscarPorRaridade(@RequestParam Raridade raridade) {
        return itemService.buscarPorRaridade(raridade);
    }

    @GetMapping("/buscar/preco")
    public List<Item> buscarPorFaixaDePreco(@RequestParam double min, @RequestParam double max) {
        return itemService.buscarPorFaixaDePreco(min, max);
    }
}
