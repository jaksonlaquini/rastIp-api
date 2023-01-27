package br.com.rastip.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rastip.core.Mail;
import br.com.rastip.core.RetornoRequest;
import br.com.rastip.model.Contato;
import br.com.rastip.model.Mensagem;
import br.com.rastip.model.Produtor;
import br.com.rastip.model.Usuario;
import br.com.rastip.service.IUsuarioSessao;
import br.com.rastip.service.ProdutorService;

@Controller
@RestController
@CrossOrigin("origem-permitida")
@RequestMapping("/produtor")
public class CadastroProdutorController {
	
	@Autowired
	private ProdutorService produtorService;
	
	@Autowired
	private IUsuarioSessao sessionUser;
	
	@RequestMapping(value = "/cadastroProdutor", method = RequestMethod.POST)
	public @ResponseBody RetornoRequest cadastroProdutor(@RequestBody Produtor produtor) {
		RetornoRequest retorno = RetornoRequest.success();
		
		try {
			if(this.validar(produtor.getEmail())){
				long idUser = sessionUser.getUserId();
				produtor.setUserId(idUser);
				Produtor prod = this.produtorService.save(produtor);
				retorno.setData(prod);
			} else {
				retorno = RetornoRequest.warn("Email inválido");
			}
		} catch (Exception e) {
			retorno = RetornoRequest.error(e.getMessage());
		}
		
		return retorno;
	}

	@GetMapping("/consultaProdutor")
	public RetornoRequest consultarProdutor(@RequestBody String documento) {
		RetornoRequest retorno = RetornoRequest.success();

		try {
			retorno.setData(this.produtorService.findByDocumento(documento));
		} catch (Exception e) {
			retorno = RetornoRequest.error(e.getMessage());
		}

		return retorno;
	}
	
	@RequestMapping(value = "/findProdutorByUser", method = RequestMethod.GET)
	public @ResponseBody RetornoRequest findProdutorByUser() {
		RetornoRequest retorno = RetornoRequest.success();

		try {
			retorno.setData(produtorService.findProdutorByUser(sessionUser.getUserId()));
		} catch (Exception e) {
			retorno = RetornoRequest.error(e.getMessage());
		}

		return retorno;
	}
	
	@GetMapping("/produtorFindAll")
	public RetornoRequest produtorFindAll() {
		RetornoRequest retorno = RetornoRequest.success();

		try {
			retorno.setData(this.produtorService.findAll());
		} catch (Exception e) {
			retorno = RetornoRequest.error(e.getMessage());
		}

		return retorno;
	}
	

	@RequestMapping(value = "/removerProdutor", method = RequestMethod.POST)
	public @ResponseBody RetornoRequest removerProdutor(@RequestBody Produtor produtor) {
		RetornoRequest retorno = RetornoRequest.success();
		
		try {
			
			this.produtorService.delete(produtor);
			
		} catch (Exception e) {
			retorno = RetornoRequest.error(e.getMessage());
		}
		
		return retorno;
	}
	
	@RequestMapping(value = "/getUsuario", method = RequestMethod.GET)
	public @ResponseBody RetornoRequest getUsuario() {
		RetornoRequest retorno = RetornoRequest.success();
		
		try {
			Usuario usuario = sessionUser.getUser();
			retorno.setData(usuario);
			
		} catch (Exception e) {
			retorno = RetornoRequest.error(e.getMessage());
		}
		
		return retorno;
	}
	
	@RequestMapping(value = "/enviarEmailContato", method = RequestMethod.POST)
	public @ResponseBody RetornoRequest enviarEmailContato(@RequestBody Contato contato) {
		RetornoRequest retorno = RetornoRequest.success();
		if(this.validar(contato.getEmail())) {
			Mensagem msg = new Mensagem("jaksonslaquini@gmail.com", contato.getEmail(), contato.getAssunto(), " Nome: " + contato.getNome() +
					"Telefone: " + contato.getTelefone() + " Mensagem: " + contato.getMensagem());
			try {
				Mail email = new Mail();
				email.enviar(msg);
				retorno.success();
				retorno.setMensagem("SUCESSO");
			} catch (Exception e) {
				retorno.error();
				retorno.setMensagem(e.getMessage());
			}
		} else {
			retorno = RetornoRequest.warn("Email inválido");
		}
		return retorno;
	}
	
	public boolean validar(String email){
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }
}
