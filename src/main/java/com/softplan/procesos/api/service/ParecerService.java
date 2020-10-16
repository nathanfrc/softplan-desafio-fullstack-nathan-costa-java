package com.softplan.procesos.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softplan.procesos.api.entity.Parecer;
import com.softplan.procesos.api.repositories.ParecerRepository;

@Service
public class ParecerService {
	private static final Logger log = LoggerFactory.getLogger(ParecerService.class);

	@Autowired
	private ParecerRepository parecerRepository;

	public void salvar(Parecer parecer) {
		log.info("Persistindo parecer: {}", parecer);
		this.parecerRepository.save(parecer);
	}

}
