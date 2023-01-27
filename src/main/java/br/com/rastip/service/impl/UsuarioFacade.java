package br.com.rastip.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.rastip.model.Usuario;
import br.com.rastip.service.IUsuarioSessao;
import br.com.rastip.service.UsuarioService;

@Component
public class UsuarioFacade implements IUsuarioSessao {
	
	@Autowired
	private UsuarioService service;

	@Override
	public Long getUserId() {
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = service.findOne(login);
		return usuario != null ? usuario.getId() : 0;
	}
	

	@Override
	public Usuario getUser() {
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = service.findOne(login);
		return usuario;
	}

}
