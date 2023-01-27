package br.com.rastip.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rastip.model.Endereco;
import br.com.rastip.repository.EnderecoRepository;
import br.com.rastip.service.EnderecoService;
import br.com.rastip.service.IUsuarioSessao;

@Service(value = "enderecoService")
public class EnderecoServiceImpl implements EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoDao;
	
	@Autowired
	private IUsuarioSessao usuario;

	@Override
	public List<Endereco> findAll() {
		List<Endereco> list = new ArrayList<>();
		enderecoDao.findByUserId(usuario.getUserId()).iterator().forEachRemaining(list::add);
		return list;
	}
	
	@Override
	public List<Endereco> findByProdutor(String produtor) {
		return enderecoDao.findByDocumento(produtor);
	}

	@Override
	public Endereco findByIdEndereco(Integer idEndereco) {
		return enderecoDao.findByIdEndereco(idEndereco);
	}

	@Override
	public Endereco save(Endereco endereco) {
		return enderecoDao.save(endereco);
	}

	@Override
	public List<Endereco> findByDocumento(String documento) {
		// TODO Auto-generated method stub
		return enderecoDao.findByDocumento(documento);
	}

	@Override
	public void delete(Endereco endereco) {
		enderecoDao.delete(endereco);
		
	}
}
