package br.com.rastip.model.wrapper;

import java.io.Serializable;

import br.com.rastip.model.Usuario;

public class CadastroUsuarioWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private String recaptcha;
	private String remoteip;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getRecaptcha() {
		return recaptcha;
	}
	public void setRecaptcha(String recaptcha) {
		this.recaptcha = recaptcha;
	}
	public String getRemoteip() {
		return remoteip;
	}
	public void setRemoteip(String remoteip) {
		this.remoteip = remoteip;
	}

	
}
