package br.com.rastip.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rastip.core.RelatorioUtil;
import br.com.rastip.model.Etiqueta;
import br.com.rastip.model.Impressao;
import br.com.rastip.repository.EnderecoRepository;
import br.com.rastip.repository.EtiquetaItemRepository;
import br.com.rastip.repository.EtiquetaRepository;
import br.com.rastip.repository.ProdutorRepository;
import br.com.rastip.service.EtiquetaService;
import br.com.rastip.service.IUsuarioSessao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Service
public class EitquetaServiceImpl implements EtiquetaService {

	@Autowired
	private EtiquetaRepository repository;
	
	@Autowired
	private ProdutorRepository produtorRep;
	
	@Autowired
	private EnderecoRepository enderecoRep;

	@Autowired
	private EtiquetaItemRepository etiquetaItemRep;
	
	@Autowired
	private IUsuarioSessao usuarioSessao;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	@Override
	public Etiqueta salvar(Etiqueta etiqueta) {
		
		Etiqueta e =  repository.saveAndFlush(etiqueta);
		/*Map<Produtor, String> ps = new TreeMap<>();
		if(e.getProdutor() == null) {
			for(Produtor p : produtorRep.findByUserId(usuarioSessao.getUserId())){
				for(Endereco end : enderecoRep.findByDocumento(p.getDocumento())){
					ps.put(p, end.getCidade());
				}
			}
		} else {
			if(e.getEndereco() == null){
				for(Endereco end : enderecoRep.findByDocumento(e.getProdutor().getDocumento())){
					ps.put(e.getProdutor(), end.getCidade());
				}
			} else {
				ps.put(e.getProdutor(), e.getEndereco());
			}
		}
		
		EtiquetaItem item;
		
		for (Map.Entry<Produtor, String> entry : ps.entrySet () ) {
			item = new EtiquetaItem();
			item.setEtiquetaId(e.getId());
			item.setProdutor(entry.getKey());
			item.setEndereco(entry.getValue());
			etiquetaItemRep.saveAndFlush(item);
		}
		*/
		return e;
	}

	@Override
	public byte[] imprimir(JasperReport relatorio, Long id) {
		System.getProperties().put("java.awt.headless", true);

        Etiqueta etiqueta = this.load(id);
        
        try {
			etiqueta.setQrcodecontent(generarQRCode(etiqueta));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        Impressao imp = new Impressao();
        
        //List<EtiquetaItem> rs = etiquetaItemRep.findByEtiquetaId(id);
        for(int i=0;i < etiqueta.getQuantidade(); i++){
        	imp.addEtiqueta(etiqueta);
        }
        
        List<Impressao> rs = Arrays.asList(imp);
        
        try {

            byte[] arquivo = RelatorioUtil.gerarRelatorio(relatorio, rs, null);

            return arquivo;

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	public Etiqueta loadByQRCode(String codigo){
		Etiqueta e = repository.findByCodLocation(codigo);
		return e;
	}

	private String generarQRCode(Etiqueta etiqueta) throws UnsupportedEncodingException {
		String identity = encoder.encode(etiqueta.getId().toString());
		
		etiqueta.setCodLocation(identity);
		
		salvar(etiqueta);
		
		String url = environment.getProperty("locationQRCode", "http://localhost:4200/#/location?key=") + URLEncoder.encode(identity,"UTF-8");
		
		return url;
	}

	@Override
	public Etiqueta load(Long id) {
		return repository.findOne(id);
	}
	
	
}
