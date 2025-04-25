package com.mercado.mercado_medieval.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercado.mercado_medieval.dto.ItemDTO;
import com.mercado.mercado_medieval.model.Item;
import com.mercado.mercado_medieval.model.Personagem;
import com.mercado.mercado_medieval.repository.ItemRepository;
import com.mercado.mercado_medieval.repository.PersonagemRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PersonagemRepository personagemRepository;

    public ItemDTO salvar(ItemDTO dto) {
        Personagem dono = null;
        if (dto.getDonoId() != null) {
            dono = personagemRepository.findById(dto.getDonoId())
                    .orElseThrow(() -> new EntityNotFoundException("Dono não encontrado com id: " + dto.getDonoId()));
        }

        Item item = new Item();
        item.setNome(dto.getNome());
        item.setTipo(dto.getTipo());
        item.setRaridade(dto.getRaridade());
        item.setPreco(dto.getPreco());
        item.setDono(dono);

        item = itemRepository.save(item);
        return toDTO(item);
    }

    public List<ItemDTO> buscarTodos() {
        return itemRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ItemDTO buscarPorId(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com id: " + id));
        return toDTO(item);
    }

    public ItemDTO atualizar(Long id, ItemDTO dto) {
        Item itemExistente = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com id: " + id));

        Personagem dono = null;
        if (dto.getDonoId() != null) {
            dono = personagemRepository.findById(dto.getDonoId())
                    .orElseThrow(() -> new EntityNotFoundException("Dono não encontrado com id: " + dto.getDonoId()));
        }

        itemExistente.setNome(dto.getNome());
        itemExistente.setTipo(dto.getTipo());
        itemExistente.setRaridade(dto.getRaridade());
        itemExistente.setPreco(dto.getPreco());
        itemExistente.setDono(dono);

        return toDTO(itemRepository.save(itemExistente));
    }

    public void deletar(Long id) {
        itemRepository.deleteById(id);
    }

    public List<ItemDTO> buscarPorNomeParcial(String nome) {
        return itemRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<ItemDTO> buscarPorTipo(String tipo) {
        return itemRepository.findByTipoIgnoreCase(tipo)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<ItemDTO> buscarPorRaridade(String raridade) {
        return itemRepository.findByRaridadeIgnoreCase(raridade)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<ItemDTO> buscarPorFaixaDePreco(double min, double max) {
        return itemRepository.findByPrecoBetween(min, max)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Conversão manual de Model para DTO
    private ItemDTO toDTO(Item item) {
        ItemDTO dto = new ItemDTO();
        dto.setId(item.getId());
        dto.setNome(item.getNome());
        dto.setTipo(item.getTipo());
        dto.setRaridade(item.getRaridade());
        dto.setPreco(item.getPreco());
        dto.setDonoId(item.getDono() != null ? item.getDono().getId() : null);
        return dto;
    }
}
