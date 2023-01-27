package br.com.rastip.service;

import java.util.List;

import br.com.rastip.model.Endereco;

/**
 *
 * @author jakson.laquini
 */
public interface EnderecoService {

	void delete(Endereco endereco);

	Endereco findByIdEndereco(Integer idEndereco);

	Endereco save(Endereco endereco);
	
	List<Endereco> findByProdutor(String produtor);
	
	List<Endereco> findAll();
    
	List<Endereco> findByDocumento(String documento);
}
