/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rastip.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author diogo.coelho
 */
@Entity
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEndereco;
	
//	@NotEmpty
	private String documento;
    private String uf;
    private String bairro;
//    @NotEmpty
    private String cidade;
    private String cep;
    private Integer numero;

	//    @NotEmpty
    private String logradouro;
    private String idLatitude;
    private String idLongitude;
    private Float idLatitudeF;
    private Float idLongitudeF;

    @ManyToOne(cascade=CascadeType.REMOVE)
    private Produtor produtor;
    
    public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

	public Integer getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
    
	public Produtor getProdutor() {
		return produtor;
	}

	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}

	public String getIdLatitude() {
		return idLatitude;
	}

	public void setIdLatitude(String idLatitude) {
		this.idLatitude = idLatitude;
		if(idLatitude != null)
				try{idLatitudeF = Float.parseFloat(idLatitude);}catch(NumberFormatException e){}
			
	}

	public String getIdLongitude() {
		return idLongitude;
	}

	public void setIdLongitude(String idLongitude) {
		this.idLongitude = idLongitude;
		if(idLongitude != null)
			try{idLongitudeF = Float.parseFloat(idLongitude);}catch(NumberFormatException e){}
	}

	public Float getIdLatitudeF() {
		return idLatitudeF;
	}

	public void setIdLatitudeF(Float idLatitudeF) {
		this.idLatitudeF = idLatitudeF;
	}

	public Float getIdLongitudeF() {
		return idLongitudeF;
	}

	public void setIdLongitudeF(Float idLongitudeF) {
		this.idLongitudeF = idLongitudeF;
	}

	@Override
	public String toString() {
		
	return this.logradouro + " - " + this.numero + ", " + this.bairro + ", " + this.cidade + "-" +  this.uf + " " + this.cep;
	}
}
