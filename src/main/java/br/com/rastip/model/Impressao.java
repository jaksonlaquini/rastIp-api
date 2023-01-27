package br.com.rastip.model;

import java.util.ArrayList;
import java.util.List;

public class Impressao {

	private List<Etiqueta> etiquetas;

	public List<Etiqueta> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(List<Etiqueta> etiquetas) {
		this.etiquetas = etiquetas;
	}

	public void addEtiqueta(Etiqueta etiqueta) {
		if(etiquetas == null)
			etiquetas = new ArrayList<>();
		etiquetas.add(etiqueta);
	}
}
