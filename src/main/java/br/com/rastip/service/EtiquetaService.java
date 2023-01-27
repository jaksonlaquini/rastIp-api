package br.com.rastip.service;

import br.com.rastip.model.Etiqueta;
import net.sf.jasperreports.engine.JasperReport;

public interface EtiquetaService {
	
	Etiqueta salvar(Etiqueta etiqueta);
	
	byte[] imprimir(JasperReport relatorio, Long id);
	
	Etiqueta load(Long id);
	
	Etiqueta loadByQRCode(String codigo);

}
