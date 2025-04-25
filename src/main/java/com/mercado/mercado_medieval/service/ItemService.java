package com.mercado.mercado_medieval.service;

import com.mercado.mercado_medieval.model.Item;
import com.mercado.mercado_medieval.model.Personagem;
import com.mercado.mercado_medieval.model.enums.RaridadeItem;
import com.mercado.mercado_medieval.model.enums.TipoItem;
import com.mercado.mercado_medieval.repository.ItemRepository;
import com.mercado.mercado_medieval.repository.PersonagemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PersonagemRepository personagemRepository;

    public Item salvar(Item item) {
        if (item.getDono() != null && item.getDono().getId() != null) {
            Personagem dono = personagemRepository.findById(item.getDono().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Dono não encontrado com id: " + item.getDono().getId()));
            item.setDono(dono);
        }
        return itemRepository.save(item);
    }

    public List<Item> buscarTodos() {
        return itemRepository.findAll();
    }

    public Item buscarPorId(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com id: " + id));
    }

    public Item atualizar(Long id, Item novosDados) {
        Item itemExistente = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com id: " + id));

        if (novosDados.getDono() != null && novosDados.getDono().getId() != null) {
            Personagem dono = personagemRepository.findById(novosDados.getDono().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Dono não encontrado com id: " + novosDados.getDono().getId()));
            itemExistente.setDono(dono);
        }

        itemExistente.setNome(novosDados.getNome());
        itemExistente.setTipo(novosDados.getTipo());
        itemExistente.setRaridade(novosDados.getRaridade());
        itemExistente.setPreco((int)novosDados.getPreco());

        return itemRepository.save(itemExistente);
    }

    public void deletar(Long id) {
        itemRepository.deleteById(id);
    }

    public List<Item> buscarPorNomeParcial(String nome) {
        return itemRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Item> buscarPorTipo(TipoItem tipo) {
        return itemRepository.findByTipo(tipo);
    }

    public List<Item> buscarPorRaridade(RaridadeItem raridade) {
        return itemRepository.findByRaridade(raridade);
    }

    public List<Item> buscarPorFaixaDePreco(double min, double max) {
        return itemRepository.findByPrecoBetween(min, max);
    }
}
