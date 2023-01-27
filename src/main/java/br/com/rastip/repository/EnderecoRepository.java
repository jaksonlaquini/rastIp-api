package br.com.rastip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.rastip.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
	
	Endereco findByIdEndereco(Integer idEndereco);
	List<Endereco> findByDocumento(String documento);
	
	@Query(value="SELECT e.* FROM Endereco e inner join Produtor p on (e.documento = p.documento) WHERE p.userId = ?1 ", nativeQuery=true)
	List<Endereco> findByUserId(Long id);

}
