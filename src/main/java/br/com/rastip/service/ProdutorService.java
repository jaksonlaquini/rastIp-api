package br.com.rastip.service;

import java.util.List;

import br.com.rastip.model.Produtor;

/**
 *
 * @author jakson.laquini
 */
public interface ProdutorService {

	void delete(String documento);
	
	void delete(Produtor produtor);

	Produtor findByDocumento(String documento);

	Produtor save(Produtor produtor) throws Exception;
		
	List<Produtor> findAll();
	
	List<Produtor> findProdutorByUser(Long userId);    
}
