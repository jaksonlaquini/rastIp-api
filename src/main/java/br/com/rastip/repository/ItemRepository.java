package br.com.rastip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rastip.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
