package com.softplan.processos.api.repositories;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.softplan.processos.api.entity.Processo;

@Transactional(readOnly = true)
@NamedQueries({
	@NamedQuery(name = "ProcessoRepository.buscarProcessosPendentesPaginacao",  
			query = "SELECT p FROM Processo p ") })
public interface ProcessoRepository extends JpaRepository<Processo, Long > {
	
	Page<Processo> findAll(Pageable paginacao);

	Page<Processo> findById(Long id, Pageable paginacao);

	@Query("SELECT p FROM Processo p left join p.parecer pa where pa.id is null ")
	Page<Processo> buscarProcessosPendentesPaginacaoTeste(Pageable paginacao);

}
