package br.com.rastip.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rastip.core.Mail;
import br.com.rastip.core.RetornoRequest;
import br.com.rastip.model.Contato;
import br.com.rastip.model.Mensagem;
import br.com.rastip.model.Usuario;

@Controller
@RestController
@CrossOrigin("origem-permitida")
@RequestMapping("/contato")
public class ContatoControler {
	@Autowired
	private Mail mail;

	@RequestMapping(value = "/enviarEmailContato", method = RequestMethod.POST)
	public @ResponseBody RetornoRequest enviarEmailContato(@RequestBody Contato contato) {
		RetornoRequest retorno = RetornoRequest.success();
		
		Mensagem msg = new Mensagem("rastipcontato@gmail.com", 
				"rastipcontato@gmail.com", contato.getAssunto(), this.template(contato));
		try {
			mail.enviar(msg);
			retorno.success();
		} catch (Exception e) {
			retorno.error();
			retorno.setMensagem("Ocorreu um erro ao tentar enviar o seu email de contato.");
		}
		
		return retorno;
	}
	
	public String template(Contato contato){
		return " <div class='ui-g-12 ui-md-12' > " +
				" <hr/>" +
			" <h3> Dados do Contato: </h3> " +
			" <p> Nome:" + contato.getNome() + "</p>" +
			" <p> Telefone: " + contato.getNrTelefoneFormatter() + "</p>" +
			" <p> Email:" + contato.getEmail() + "</p>" +
			" <hr/>" +
			" <h3> Assunto: </h3>" +
			" <p> Mensagem: " + contato.getMensagem() + "</p>" +
			" <hr/>" +
			" <h5> <span><i class='fa fa-registered'></i>RASTIP - Tecnology</span></h5>" +
			"</div>";	
	}
} 
 
