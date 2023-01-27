package br.com.rastip.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rastip.model.Produtor;
import br.com.rastip.repository.ProdutorRepository;
import br.com.rastip.service.IUsuarioSessao;
import br.com.rastip.service.ProdutorService;

@Service(value = "produtorService")
public class ProdutorServiceImpl implements ProdutorService {
	
	@Autowired
	private ProdutorRepository produtorDao;
	
	@Autowired
	private IUsuarioSessao usuarioSessao;

	@Override
	public List<Produtor> findAll() {
		List<Produtor> list = new ArrayList<>();
		produtorDao.findByUserId(usuarioSessao.getUserId()).iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public Produtor findByDocumento(String documento) {
		return produtorDao.findByDocumento(documento);
	}

	@Override
    public Produtor save(Produtor produtor)throws Exception {
		produtor.setUserId(usuarioSessao.getUserId());
		try {
			 return produtorDao.save(produtor);
		} catch (Exception e) {
			throw new Exception(e.getMessage());// TODO: handle exception
		}
       
    }

	@Override
	public void delete(Produtor produtor) {
		produtorDao.delete(produtor);
		
	}

	@Override
	public List<Produtor> findProdutorByUser(Long userId) {
		// TODO Auto-generated method stub
		return produtorDao.findByUserId(userId);
	}

	@Override
	public void delete(String documento) {
		// TODO Auto-generated method stub
		
	}

}
