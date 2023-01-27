package br.com.rastip.core;

import java.io.Serializable;

public class RetornoRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private RetornoRequest(Tipo status){
		this.status = status;
	}
	
	public static RetornoRequest success(){
		return new RetornoRequest(Tipo.success);
	}
	
	public static RetornoRequest info(){
		return new RetornoRequest(Tipo.info);
	}
	
	public static RetornoRequest warn(){
		return new RetornoRequest(Tipo.warn);
	}
	
	public static RetornoRequest error(){
		return new RetornoRequest(Tipo.error);
	} 
	
	private Object data;
	private final Tipo status;
	private String mensagem;
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Tipo getStatus() {
		return status;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public static RetornoRequest info(String mensagem) {
		RetornoRequest rr = RetornoRequest.info();
		rr.setMensagem(mensagem);
		return rr;
	}
	public static RetornoRequest warn(String mensagem) {
		RetornoRequest rr = RetornoRequest.warn();
		rr.setMensagem(mensagem);
		return rr;
	}
	public static RetornoRequest error(String mensagem) {
		RetornoRequest rr = RetornoRequest.error();
		rr.setMensagem(mensagem);
		return rr;
	}

}
