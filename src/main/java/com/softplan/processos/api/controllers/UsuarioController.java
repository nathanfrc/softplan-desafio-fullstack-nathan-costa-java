package com.softplan.processos.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softplan.processos.api.responses.Response;
import com.softplan.processos.api.dto.UsuarioDto;
import com.softplan.processos.api.entity.Processo;
import com.softplan.processos.api.entity.ProcessoUsuario;
import com.softplan.processos.api.entity.Usuario;
import com.softplan.processos.api.service.ProcessoService;
import com.softplan.processos.api.service.ProcessoUsuarioService;
import com.softplan.processos.api.service.UsuarioService;

//import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ProcessoService processoService;
	
	@Autowired
	private ProcessoUsuarioService processoUsuarioServiceService;
	
	public UsuarioController() {
	}

	@GetMapping
	@Cacheable(value = "findAll")
	public Page<Usuario> findAll(@PageableDefault(direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		Page<Usuario> usuarios = usuarioService.findAll(paginacao);
		return usuarios;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Page<Usuario> buscaUser(@PageableDefault(direction = Direction.DESC, page = 0, size = 10) Pageable paginacao,
			@PathVariable("id") Long id) {
		Page<Usuario> usuario = usuarioService.findByIdUsuario(paginacao, id);
		return usuario;
	}

	@PostMapping
	public ResponseEntity<Response<UsuarioDto>> cadastrar(@Valid @RequestBody UsuarioDto usuarioDto,
			BindingResult result) throws NoSuchAlgorithmException, SQLIntegrityConstraintViolationException {
		log.info("Cadastrando Usuario: {}", usuarioDto.toString());
		Response<UsuarioDto> response = new Response<UsuarioDto>();

		try {
			Usuario usuario = usuarioDto.converterUsuarioDto(usuarioDto, result);

			Optional<Usuario> usOptional = this.usuarioService.buscarPorEmail(usuario.getEmail());

			if (usOptional.isPresent()) {
				response.getErrors().add("Usuario já existe com esse email = "+usuario.getEmail());
				return ResponseEntity.badRequest().body(response);
			}

			final Usuario us = this.usuarioService.persistir(usuario);
			response.setContent(usuario.converterUsuario(us, result));
			return ResponseEntity.ok(response);

		} catch (Exception e) {

			log.info("Opss! erro inesperado");
			response.getErrors().add("Opss! erro inesperado");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<UsuarioDto>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result) throws NoSuchAlgorithmException {
		log.info("Atualizando usuario: {}", usuarioDto.toString());
		Response<UsuarioDto> response = new Response<UsuarioDto>();

		Usuario usuario = usuarioDto.converterUsuarioDto(usuarioDto, result);

		Optional<Usuario> us = this.usuarioService.findById(id);

		if (!us.isPresent()) {
			log.info("Erro ao remover devido ao ID: {} ser inválido.", id);
			response.getErrors().add("Erro ao remover usuario. Registro não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		@SuppressWarnings("static-access")
		final Usuario usFinal = this.usuarioService.persistir(usuarioDto.mergeObjetos(usuarioDto, us.get()));
		response.setContent(usuario.converterUsuario(usFinal, result));
		return ResponseEntity.ok(response);
	}
	
	
	@DeleteMapping(value = "/{id}")
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {
		log.info("Removendo Usuario: {}", id);
		Response<String> response = new Response<String>();
		Optional<Usuario> usuario = this.usuarioService.findById(id);

		if (!usuario.isPresent()) {
			log.info("Erro ao remover devido ao ID: {} ser inválido.", id);
			response.getErrors().add("Erro ao remover usuario. Registro não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		this.usuarioService.remover(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	/**
	 * Atribuir um processo ao usuario
	 */
	@SuppressWarnings("null")
	@PostMapping(value = "/{id}/processo/{idProcesso}")
	public ResponseEntity<Response<UsuarioDto>> atribuirProcesso(
			@PathVariable("id") Long idUser,@PathVariable("idProcesso") Long idProcesso )  {
		Response<UsuarioDto> response = new Response<UsuarioDto>();
		try {
			
			Optional<Usuario> usOptional = this.usuarioService.findById(idUser);

			if (!usOptional.isPresent()) {
				log.info("Usuário não existe");
				response.getErrors().add("Usuário não existe");
				return ResponseEntity.badRequest().body(response);
			}
			
			Optional<Processo> processoOptional = this.processoService.findByIdProcesso(idProcesso);
			
			if (!processoOptional.isPresent()) {
				log.info("Processo não existe");
				response.getErrors().add("Processo não existe");
				return ResponseEntity.badRequest().body(response);
			}
			
			
			ProcessoUsuario processoUsuario = new ProcessoUsuario();
			
			processoUsuario.setProcesso(processoOptional.get());
			processoUsuario.setUsarios(usOptional.get());
			
			this.processoUsuarioServiceService.salvar(processoUsuario);
			
	
			return ResponseEntity.ok(response);

		} catch (Exception e) {

			log.info("Opss! erro inesperado");
			response.getErrors().add("Opss! erro inesperado");
			return ResponseEntity.badRequest().body(response);
		}
	}


}
