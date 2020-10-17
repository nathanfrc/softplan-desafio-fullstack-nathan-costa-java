package com.softplan.processos.api.repositories;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.softplan.processos.api.entity.Parecer;
import com.softplan.processos.api.entity.Processo;

@Transactional(readOnly = true)
@NamedQueries({
	@NamedQuery(name = "ParecerRepository.findByProcessoId", 
			query = "SELECT p FROM Parecer p WHERE p.id = :id") })
public interface ParecerRepository extends JpaRepository<Parecer, Long > {

	Page<Parecer> findById(Long id, Pageable paginacao);

}
