package com.softplan.procesos.api.repositories;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.softplan.procesos.api.entity.Processo;
import com.softplan.procesos.api.entity.ProcessoUsuario;

@Transactional(readOnly = true)
@NamedQueries({
	@NamedQuery(name = "ProcessoUsuarioRepository.findByProcessoId", 
			query = "SELECT p FROM ProcessoUsuario p WHERE p.id = :id") })
public interface ProcessoUsuarioRepository extends JpaRepository<ProcessoUsuario, Long > {


}
