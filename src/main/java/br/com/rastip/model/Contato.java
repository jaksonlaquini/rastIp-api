package br.com.rastip.model;

public class Contato {
	 private String nome;
	 private String email;
	 private String assunto;
	 private long telefone;
	 private String mensagem;
	 private String nrTelefoneFormatter;
	 
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public long getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getNrTelefoneFormatter() {
		return nrTelefoneFormatter;
	}
	public void setNrTelefoneFormatter(String nrTelefoneFormatter) {
		this.nrTelefoneFormatter = nrTelefoneFormatter;
	}
	
	

}
