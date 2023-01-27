package br.com.rastip.service;

import java.util.List;

import br.com.rastip.model.Produto;

/**
 *
 * @author jakson.laquini
 */
public interface ProdutoItemService {

	void delete(long id);

	Produto findById(long id);

	Produto save(Produto produto);
	
	List<Produto> findAll();
    
}
