package com.softplan.procesos.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softplan.procesos.api.entity.Usuario;
import com.softplan.procesos.api.response.Response;


@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	
	@Autowired
	private com.softplan.procesos.api.service.UsuarioService usuarioService;
	
	
	public UsuarioController() {
	}

	@GetMapping
	@Cacheable(value = "findAll")
	public Page<Usuario>findAll(@PageableDefault(direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		
		Response<Usuario> response = new Response<Usuario>();
		Page<Usuario> usuarios = usuarioService.findAll(paginacao);
		return usuarios;
	}
	
}
