package br.com.rastip.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rastip.model.Produto;
import br.com.rastip.repository.ProdutoItemRepository;
import br.com.rastip.service.ProdutoItemService;

@Service(value = "produtoItemService")
public class ProdutoItemServiceImpl implements ProdutoItemService {
	
	@Autowired
	private ProdutoItemRepository produtoItemDao;

	@Override
	public List<Produto> findAll() {
		List<Produto> list = new ArrayList<>();
		produtoItemDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	@Override
	public void delete(long id) {
		produtoItemDao.delete(id);
		
	}

	@Override
	public Produto findById(long id) {
		// TODO Auto-generated method stub
		return produtoItemDao.findById(id);
	}

	@Override
	public Produto save(Produto produto) {
		// TODO Auto-generated method stub
		return produtoItemDao.save(produto);
	}

}
