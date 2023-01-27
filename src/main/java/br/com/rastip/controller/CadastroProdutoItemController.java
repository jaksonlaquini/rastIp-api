package br.com.rastip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rastip.core.RetornoRequest;
import br.com.rastip.model.Produto;
import br.com.rastip.service.ProdutoItemService;

@Controller
@RestController
@CrossOrigin("origem-permitida")
@RequestMapping("/produto")
public class CadastroProdutoItemController {
	
	@Autowired
	private ProdutoItemService produtoItemService;
	
	@RequestMapping(value = "/cadastroProdutoItem", method = RequestMethod.POST)
	public @ResponseBody RetornoRequest cadastroProdutoItem(@RequestBody Produto produto) {
		RetornoRequest retorno = RetornoRequest.success();
		
		try {
			Produto prod = this.produtoItemService.save(produto);
			retorno.setData(prod);
			
		} catch (Exception e) {
			retorno = RetornoRequest.error(e.getMessage());
		}
		
		return retorno;
	}

	@GetMapping("/consultaProdutoItem")
	public RetornoRequest consultaProdutoItem(@RequestBody long id) {
		RetornoRequest retorno = RetornoRequest.success();

		try {
			retorno.setData(this.produtoItemService.findById(id));
		} catch (Exception e) {
			retorno = RetornoRequest.error(e.getMessage());
		}

		return retorno;
	}
	
//	@GetMapping("/consultaProdutoItem")
//	public RetornoRequest consultaProdutoItem(String documento, Integer idEndereco) {
//		RetornoRequest retorno = RetornoRequest.success();
//
//		try {
//			boolean filterDocumento = documento != null && !documento.isEmpty();
//			boolean filterEndereco = idEndereco != null && idEndereco != 0;
//			if(filterDocumento && filterEndereco)
////				retorno.setData(this.produtoItemService.findById(documento, idEndereco));
//			else if (filterDocumento)
////				retorno.setData(this.produtoItemService.findById(documento));
//			else
//				retorno.setData(this.produtoItemService.findById(idEndereco));
//		} catch (Exception e) {
//			retorno = RetornoRequest.error(e.getMessage());
//		}
//
//		return retorno;
//	}
//	
	@GetMapping("/produtoItemFindAll")
	public RetornoRequest produtoItemFindAll() {
		RetornoRequest retorno = RetornoRequest.success();

		try {
			retorno.setData(this.produtoItemService.findAll());
		} catch (Exception e) {
			retorno = RetornoRequest.error(e.getMessage());
		}

		return retorno;
	}
	
	@RequestMapping(value = "/removerProduto", method = RequestMethod.POST)
	public @ResponseBody RetornoRequest removerEndereco(@RequestBody Produto produto) {
		RetornoRequest retorno = RetornoRequest.success();
		
		try {
				
			this.produtoItemService.delete(produto.getId());
			
		} catch (Exception e) {
			retorno = RetornoRequest.error(e.getMessage());
		}
		
		return retorno;
	}
}
