package com.softplan.procesos.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softplan.procesos.api.dto.UsuarioDto;
import com.softplan.procesos.api.entity.Usuario;
import com.softplan.procesos.api.response.Response;
import com.softplan.procesos.api.service.UsuarioService;

//import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	public UsuarioController() {
	}

	@GetMapping
	@Cacheable(value = "findAll")
	public Page<Usuario>findAll(@PageableDefault(direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		Page<Usuario> usuarios = usuarioService.findAll(paginacao);
		return usuarios;
	}
	
	
	 @RequestMapping(value = "/{id}",
	  method = RequestMethod.GET)
		public Page<Usuario> buscaUser(@PageableDefault(direction = Direction.DESC, page = 0, size = 10) Pageable paginacao,@PathVariable("id") Long id) {
		  Page<Usuario> usuario = usuarioService.findByIdUsuario(paginacao,id);
		  return usuario;
		}
	
	/*@GetMapping(value = "/{id}")
	public ResponseEntity<Response<UsuarioDto>> findById(@PathVariable("id") Long id) {
		
		System.out.println("entrou");
		
		Response<UsuarioDto> response = new Response<UsuarioDto>();
		Optional<Usuario> usuario = usuarioService.findById(id);
		
		if(!usuario.isPresent()) {
			log.error("Usuario: não encontrado");
			response.getErrors().add("Usuario: não encontrado ");
			return ResponseEntity.badRequest().body(response);
		}
		
	    UsuarioDto usuarioDto =	this.converterUsuario(usuario.get());
	    
	    System.out.println(usuarioDto);
		
		response.setContent(usuarioDto);
		return ResponseEntity.ok(response);
	}*/
	
	@PostMapping
	public ResponseEntity<Response<UsuarioDto>> cadastrar(@Valid @RequestBody UsuarioDto usuarioDto,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Usuario: {}",usuarioDto.toString());
		Response<UsuarioDto> response = new Response<UsuarioDto>();
		
		Usuario usuario = usuarioDto.converterUsuarioDto(usuarioDto,result);
		/*validarDadosExistentes(usuario, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de usuario: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}*/
	
	    final Usuario us = this.usuarioService.persistir(usuario);
	    
	    System.out.println(us);
		response.setContent(usuario.converterUsuario(us, result));
		return ResponseEntity.ok(response);
	}
	

public UsuarioDto converterUsuario(Usuario usuario) {
		
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setId(usuario.getId());
		usuarioDto.setEmail(usuario.getEmail());
		usuarioDto.setNome(usuario.getNome());
		usuarioDto.setSenha(usuario.getSenha().toString());
		usuarioDto.setPerfil(usuario.getPerfil());
		usuarioDto.setDataCriacao(usuario.getDataCriacao());
		
		return usuarioDto;
	}

/*private void validarDadosExistentes(Usuario usuario, BindingResult result) {
	this.usuarioService.buscarPorEmail(usuario.getEmail())
		.ifPresent(func -> result.addError(new ObjectError("Usuario", "Email já existente.")));
}*/

public UsuarioDto converterUsuario(Usuario usuario, BindingResult result) {
	
	UsuarioDto usuarioDto = new UsuarioDto();
	usuarioDto.setId(usuario.getId());
	usuarioDto.setEmail(usuario.getEmail());
	usuarioDto.setNome(usuario.getNome());
	usuarioDto.setSenha(usuario.getSenha().toString());
	usuarioDto.setPerfil(usuario.getPerfil());
	usuarioDto.setDataCriacao(usuario.getDataCriacao());

	return usuarioDto;
}


	
}
