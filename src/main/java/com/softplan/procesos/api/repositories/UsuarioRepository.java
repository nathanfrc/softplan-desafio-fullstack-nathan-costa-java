package com.softplan.procesos.api.repositories;


import java.util.Optional;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.softplan.procesos.api.entity.Usuario;

@Transactional(readOnly = true)
@NamedQueries({
	@NamedQuery(name = "UsuarioRepository.findByUsuarioId", 
			query = "SELECT us FROM Usuario us WHERE us.id = :usuarioId") })
public interface UsuarioRepository extends JpaRepository<Usuario, Long > {

	Usuario findByEmail(String email);
	//Usuario findByUsuarioId(@Param("usuarioId") Long usuarioId);

	Page<Usuario> findById(Long id, Pageable paginacao);

}
