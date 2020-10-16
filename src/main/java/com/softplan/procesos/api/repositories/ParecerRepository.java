package com.softplan.procesos.api.repositories;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.softplan.procesos.api.entity.Processo;

@Transactional(readOnly = true)
@NamedQueries({
	@NamedQuery(name = "ProcessoRepository.findByProcessoId", 
			query = "SELECT p FROM Processo p WHERE p.id = :id") })
public interface ParecerRepository extends JpaRepository<Processo, Long > {

	Page<Processo> findById(Long id, Pageable paginacao);

}
