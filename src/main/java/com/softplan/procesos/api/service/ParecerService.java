package com.softplan.procesos.api.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softplan.procesos.api.entity.Processo;
import com.softplan.procesos.api.entity.ProcessoUsuario;
import com.softplan.procesos.api.repositories.ParecerRepository;
import com.softplan.procesos.api.repositories.ProcessoRepository;


@Service
public class ParecerService {
	private static final Logger log = LoggerFactory.getLogger(ParecerService.class);

	@Autowired
	private ParecerRepository parecerRepository;

	/*public Page<ProcessoUsuario> findAll(Pageable paginacao) {
		this.parecerRepository.finAll
	}*/
	
	

}
