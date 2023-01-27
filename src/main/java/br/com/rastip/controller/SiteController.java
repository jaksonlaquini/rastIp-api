package br.com.rastip.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.rastip.core.NegocioException;
import br.com.rastip.core.RetornoRequest;
import br.com.rastip.model.Endereco;
import br.com.rastip.model.Etiqueta;
import br.com.rastip.model.Produto;
import br.com.rastip.model.Produtor;
import br.com.rastip.service.EtiquetaService;

@Controller
@RequestMapping("/site")
public class SiteController {

	@Autowired
	private EtiquetaService service;
	
	@GetMapping("/home")
	public ModelAndView home(){
		ModelAndView mv = new ModelAndView("site/home");
		return mv;
	}
	
	@GetMapping("/contact")
	public ModelAndView contact(){
		ModelAndView mv = new ModelAndView("site/contact");
		return mv;
	}

	@GetMapping("/location")
	public ModelAndView location(@RequestParam("key") String codigo){
		ModelAndView mv = new ModelAndView("site/location");
		try{
			Etiqueta e = service.loadByQRCode(codigo);
			
			if(e == null){
				throw new NegocioException("Código inválido!");
//				e = new Etiqueta();
//				e.setDataProducao(new Date());
//				e.setLote("23ij29");
//				e.setQuantidade(2);
//				e.setEndereco(new Endereco());
//				e.getEndereco().setBairro("MORRO DO PARAÍSO");
//				e.getEndereco().setCep("29000-000");
//				e.getEndereco().setCidade("ICONHA");
//				e.getEndereco().setLogradouro("R. MAJ. VIÊIRA");
//				e.getEndereco().setNumero(141);
//				e.getEndereco().setUf("ES");
//				e.getEndereco().setIdLatitude("-20.7954943");
//				e.getEndereco().setIdLongitude("-40.8128502");
//				e.setProdutor(new Produtor());
//				e.getProdutor().setEmail("JOAODOCAMINHAO@HOTMAIL.COM");
//				e.getProdutor().setNome("JOAO DO CAMINHAO");
//				e.getProdutor().setTelefone(1231232322l);
//				e.getProdutor().setDocumento("123.123.123-21");
//				e.setProduto(new Produto());
//				e.getProduto().setDescricao("BANANA");
//				e.getProduto().setVariedade("DA TERRA");
			}
			
			mv.addObject("etiqueta", e);
			
		}catch (Exception e) {
			e.printStackTrace();
			mv.addObject("mensagem", e.getMessage());
		}
		return mv;
	}

}
