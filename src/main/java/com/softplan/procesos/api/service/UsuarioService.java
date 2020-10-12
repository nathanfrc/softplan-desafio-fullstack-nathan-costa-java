package com.softplan.procesos.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softplan.procesos.api.repositories.UsuarioRepository;
import com.softplan.procesos.api.entity.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Page<Usuario> findAll(Pageable paginacao) {
		return this.usuarioRepository.findAll(paginacao); 
	}

}
