package com.softplan.processos.api.repositories;


import java.util.Optional;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.softplan.processos.api.entity.Usuario;

@Transactional(readOnly = true)
@NamedQueries({
	@NamedQuery(name = "UsuarioRepository.findByUsuarioId", 
			query = "SELECT us FROM Usuario us WHERE us.id = :usuarioId") })
public interface UsuarioRepository extends JpaRepository<Usuario, Long > {

	Optional<Usuario> findByEmail(String email);

	@Query("SELECT u FROM Usuario u where u.email = :email")
	Page<Usuario> findByEmailPage(String email,Pageable paginacao);
	
	Page<Usuario> findById(Long id, Pageable paginacao);
}
