package br.com.rastip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rastip.model.Produto;

public interface ProdutoItemRepository extends JpaRepository<Produto, Long> {
	
	Produto findById(Long id);

}
