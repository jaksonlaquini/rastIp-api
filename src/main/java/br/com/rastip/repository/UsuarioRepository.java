package br.com.rastip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rastip.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {
	Usuario findByLogin(String login);
}
