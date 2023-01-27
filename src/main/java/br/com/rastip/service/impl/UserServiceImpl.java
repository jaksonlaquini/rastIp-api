package br.com.rastip.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.rastip.model.Usuario;
import br.com.rastip.repository.UsuarioRepository;
import br.com.rastip.service.UsuarioService;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UsuarioService {
	
	@Autowired
	private UsuarioRepository userDao;

	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Usuario user = userDao.findByLogin(userId);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPass(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<Usuario> findAll() {
		List<Usuario> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userDao.delete(id);
	}

	@Override
	public Usuario findOne(String username) {
		return userDao.findByLogin(username);
	}

	@Override
	public Usuario findById(Long id) {
		return userDao.findOne(id);
	}

	@Override
    public Usuario save(Usuario user) throws Exception {
		try {
			  return userDao.save(user);
		} catch (Exception e) {
			throw new Exception(e.getMessage());// TODO: handle exception
		}
      
    }

	@Override
	public Usuario findByLogin(String login) {
		return userDao.findByLogin(login);
	}

}
