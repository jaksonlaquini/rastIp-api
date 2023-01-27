package br.com.rastip.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rastip.core.NegocioException;
import br.com.rastip.core.RetornoRequest;
import br.com.rastip.model.Etiqueta;
import br.com.rastip.service.EtiquetaService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@RestController
@CrossOrigin("origem-permitida")
@RequestMapping("/etiqueta")
public class EtiquetaController {
	
	@Autowired
	private EtiquetaService service;

	@PostMapping("/salvar")
	public @ResponseBody RetornoRequest salvarEtiqueta(@RequestBody Etiqueta etiqueta) {
		RetornoRequest r = RetornoRequest.success();
		try{
			if(etiqueta == null || etiqueta.getDataProducao() == null
					|| etiqueta.getEndereco() == null || etiqueta.getLote() == null
					|| etiqueta.getProduto() == null || etiqueta.getProdutor() == null
					|| etiqueta.getQuantidade() == null){
				throw new NegocioException("Necessário preencher todos os campos do formulário!");
			}
			r.setData(service.salvar(etiqueta));
		}catch (Exception e) {
			r = RetornoRequest.error(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/downloadFile/{id}")
    public @ResponseBody byte[] downloadFile(@PathVariable Long id, HttpServletRequest request) {
        // Load file as Resource

        java.io.InputStream jasperStream = this.getClass().getResourceAsStream("/etiqueta.jasper");
		
		// Cria o objeto JaperReport com o Stream do arquivo jasper
		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		
			byte[] resource = service.imprimir(jasperReport, id);
        // Try to determine file's content type
			String contentType = "application/pdf";

			return resource;
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
	
	@GetMapping("/location/{codigo}")
	public @ResponseBody RetornoRequest location(@PathVariable String codigo) {
		RetornoRequest r = RetornoRequest.success();
		try{
			Etiqueta e = service.loadByQRCode(codigo);
			
			if(e == null)
				throw new NegocioException("Código inválido!");
			
			r.setData(e);
			
		}catch (Exception e) {
			r = RetornoRequest.error(e.getMessage());
		}
		return r;
	}
    
}
