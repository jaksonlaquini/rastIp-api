package br.com.rastip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rastip.model.Etiqueta;

public interface EtiquetaRepository extends JpaRepository<Etiqueta, Long> {
	
	Etiqueta findByCodLocation(String codigo);

}
