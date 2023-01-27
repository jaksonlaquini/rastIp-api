/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rastip.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author diogo.coelho
 */
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"documento"})})
public class Produtor implements Comparable<Produtor> {
    
	
	@Column(name = "documento")
	@NotNull
    private String documento;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProdutor;
	
	@NotNull
    private String nome;
    private Long telefone;
    private String email;
    private Long userId;
    
    @OneToMany(mappedBy="produtor",fetch=FetchType.LAZY)
    private List<Endereco> enderecos;

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getIdProdutor() {
		return idProdutor;
	}

	public void setIdProdutor(int idProdutor) {
		this.idProdutor = idProdutor;
	}

	@Override
	public int compareTo(Produtor o) {
		return this.getNome() != null && o != null && o.getNome() != null ? this.getNome().compareTo(o.getNome()) : 0;
	}
    
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
}
