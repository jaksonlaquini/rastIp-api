/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rastip.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author diogo.coelho
 */
@Entity
public class Etiqueta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Endereco.class)
	private Endereco endereco;
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Produtor.class)
	private Produtor produtor;
	private Integer quantidade;
	private String lote;
	private String codLocation;
	@Temporal(TemporalType.DATE)
	private Date dataProducao;
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Produto.class)
	private Produto produto;
	@Transient
	private String qrcodecontent;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Produtor getProdutor() {
		return produtor;
	}
	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public Date getDataProducao() {
		return dataProducao;
	}
	public void setDataProducao(Date dataProducao) {
		this.dataProducao = dataProducao;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public String getQrcodecontent() {
		return qrcodecontent;
	}
	public void setQrcodecontent(String qrcodecontent) {
		this.qrcodecontent = qrcodecontent;
	}
	public void setCodLocation(String identity) {
		this.codLocation = identity;
	}
	public String getCodLocation() {
		return codLocation;
	}
}
