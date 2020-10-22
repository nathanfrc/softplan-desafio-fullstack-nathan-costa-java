package com.softplan.processos.api.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softplan.processos.api.entity.Processo;
import com.softplan.processos.api.repositories.ProcessoRepository;


@Service
public class ProcessoService {
	private static final Logger log = LoggerFactory.getLogger(ProcessoService.class);

	@Autowired
	private ProcessoRepository processoRepository;
	
	public Processo persistir(Processo processos) {
		log.info("Persistindo processos: {}", processos);
		return this.processoRepository.save(processos);
	}

	public Page<Processo> findAllProcess(Pageable paginacao) {
		return this.processoRepository.findAll(paginacao);
	}

	public Page<Processo> findByIdProcesso(Pageable paginacao, Long id) {
		
		return this.processoRepository.findById(id,paginacao);
	}
	
     public Optional<Processo> findByIdProcesso(Long id) {
		return this.processoRepository.findById(id);
	}

	public Page<Processo> findAllPendentes(Pageable paginacao) {
		log.info("Busca de pendentes {}");
		return this.processoRepository.buscarProcessosPendentesPaginacaoTeste(paginacao);
	}


	/*public Optional<Processos> buscarPorIds(Long id) {
		log.info("Buscando processo pelo IDl {}", id);
		return this.processosRepository.findById(id);
	}*/


/*	@Override
	public Page<Processos> findAllProcessoUsuarioId(Pageable paginacao,Long id) {
		log.info("Buscando processo pelo id de usaurio IDl {}", id);
		return this.processosRepository.findProcessoUsuarioId(paginacao,id);
	}*/

}
