package com.mercado.mercado_medieval.repository;

import com.mercado.mercado_medieval.model.Item;
import com.mercado.mercado_medieval.model.enums.RaridadeItem;
import com.mercado.mercado_medieval.model.enums.TipoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    
    List<Item> findByNomeContainingIgnoreCase(String nome);

    List<Item> findByTipo(TipoItem tipo);
    List<Item> findByRaridade(RaridadeItem raridade);

    List<Item> findByPrecoBetween(Double precoMin, Double precoMax);
}
