package br.com.rastip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rastip.model.Produtor;

public interface ProdutorRepository extends JpaRepository<Produtor, String> {
	
	Produtor findByDocumento(String documento);
	List<Produtor> findByUserId(Long id);
//	Produtor findByToken(String token);
	
//	 <S extends T> S save(S entity); 
	void delete(String documento);

//	Usuario findByEmailIgnoreCase(String email);
//
//	Usuario findByTokenRecupera(String token);
//
//	@Query(value="SELECT * FROM BOP_USUARIOS WHERE NM_ABREV_USUARIO = ?1 AND ID_SENHA = ?2 ", nativeQuery=true)
//	Usuario verificaUsuario(String name, String password);
//	
//	@Modifying
//	@Query(value = "UPDATE BOP_USUARIOS SET ID_SENHA = ?1 WHERE CD_USUARIO = ?2", nativeQuery = true)
//	void alterarSenha(String senha, Long cdUsuario);
}
