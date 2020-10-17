package com.softplan.processos.api.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softplan.processos.api.entity.Processo;
import com.softplan.processos.api.entity.ProcessoUsuario;
import com.softplan.processos.api.repositories.ProcessoRepository;
import com.softplan.processos.api.repositories.ProcessoUsuarioRepository;


@Service
public class ProcessoUsuarioService {
	private static final Logger log = LoggerFactory.getLogger(ProcessoUsuario.class);

	@Autowired
	private ProcessoUsuarioRepository processoUsuarioRepository;


	public void salvar(ProcessoUsuario processoUsuario) {
		// TODO Auto-generated method stub
		this.processoUsuarioRepository.save(processoUsuario);
		
	}
	
	

}
