package br.com.rastip.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class EtiquetaItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long etiquetaId;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	private Produtor produtor;
	@ManyToOne(fetch=FetchType.EAGER)
	private Produto produto;
	private String endereco;
	@Transient
	private String qrcodecontent;
			
			
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQrcodecontent() {
		return qrcodecontent;
	}
	public void setQrcodecontent(String qrcodecontent) {
		this.qrcodecontent = qrcodecontent;
	}
	public Produtor getProdutor() {
		return produtor;
	}
	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Long getEtiquetaId() {
		return etiquetaId;
	}
	public void setEtiquetaId(Long etiquetaId) {
		this.etiquetaId = etiquetaId;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
