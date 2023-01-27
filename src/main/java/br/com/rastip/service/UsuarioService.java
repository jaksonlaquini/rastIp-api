/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rastip.service;

import java.util.List;

import br.com.rastip.model.Produtor;
import br.com.rastip.model.Usuario;

/**
 *
 * @author diogo.coelho
 */
public interface UsuarioService {

	void delete(long id);

	Usuario findOne(String login);

	Usuario findById(Long id);

	Usuario save(Usuario user) throws Exception;;
	
	Usuario findByLogin(String login);
	
	List<Usuario> findAll();
    
}
