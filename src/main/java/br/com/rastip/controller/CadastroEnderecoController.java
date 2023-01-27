package br.com.rastip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rastip.core.RetornoRequest;
import br.com.rastip.model.Endereco;
import br.com.rastip.service.EnderecoService;

@Controller
@RestController
@CrossOrigin("origem-permitida")
@RequestMapping("/endereco")
public class CadastroEnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@RequestMapping(value = "/cadastroEndereco", method = RequestMethod.POST)
	public @ResponseBody RetornoRequest cadastroEndereco(@RequestBody Endereco endereco) {
		RetornoRequest retorno = RetornoRequest.success();
		
		try {
			
			Endereco end = this.enderecoService.save(endereco);
			retorno.setData(end);
			
		} catch (Exception e) {
			retorno = RetornoRequest.error(e.getMessage());
		}
		
		return retorno;
	}

	@GetMapping("/consultaEndereco")
	public RetornoRequest consultaEndereco(@RequestBody Integer idEndereco) {
		RetornoRequest retorno = RetornoRequest.success();

		try {
			retorno.setData(this.enderecoService.findByIdEndereco(idEndereco));
		} catch (Exception e) {
			retorno = RetornoRequest.error(e.getMessage());
		}

		return retorno;
	}
	
	@RequestMapping(value = "/enderecoFindByProdutor", method = RequestMethod.GET)
	public @ResponseBody RetornoRequest enderecoFindByProdutor(@RequestParam("documento") String documento) {
		RetornoRequest retorno = RetornoRequest.success();

		try {
			retorno.setData(this.enderecoService.findByDocumento(documento));
		} catch (Exception e) {
			retorno = RetornoRequest.error(e.getMessage());
		}

		return retorno;
	}
	
	@GetMapping("/enderecoFindAll")
	public RetornoRequest enderecoFindAll() {
		RetornoRequest retorno = RetornoRequest.success();

		try {
			retorno.setData(this.enderecoService.findAll());
		} catch (Exception e) {
			retorno = RetornoRequest.error(e.getMessage());
		}

		return retorno;
	}
	
	@RequestMapping(value = "/removerEndereco", method = RequestMethod.POST)
	public @ResponseBody RetornoRequest removerEndereco(@RequestBody Endereco endereco) {
		RetornoRequest retorno = RetornoRequest.success();
		
		try {
				
			this.enderecoService.delete(endereco);
			
		} catch (Exception e) {
			retorno = RetornoRequest.error(e.getMessage());
		}
		
		return retorno;
	}
}
