package br.com.rastip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rastip.model.EtiquetaItem;

public interface EtiquetaItemRepository extends JpaRepository<EtiquetaItem, Long> {
	
	List<EtiquetaItem> findByEtiquetaId(Long id);

}
