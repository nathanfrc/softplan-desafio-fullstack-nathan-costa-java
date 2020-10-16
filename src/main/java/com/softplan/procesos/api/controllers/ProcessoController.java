package com.softplan.procesos.api.controllers;

import java.security.NoSuchAlgorithmException;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softplan.procesos.api.dto.ProcessoDto;
import com.softplan.procesos.api.entity.Processo;
import com.softplan.procesos.api.entity.Usuario;
import com.softplan.procesos.api.response.Response;
import com.softplan.procesos.api.service.ProcessoService;

@RestController
@RequestMapping("/api/processo")
@CrossOrigin(origins = "*")
public class ProcessoController {

	private static final Logger log = LoggerFactory.getLogger(ProcessoController.class);
	
    @Autowired 
	private ProcessoService processoService;
    
   
	public ProcessoController() {
	}
	
	
	@GetMapping(value="/pendentes")
	@Cacheable(value = "findAllPendente")
	public Page<Processo>findAllPendente(@PageableDefault(direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		log.info(""
				+ "busca de processo: {}");
		@SuppressWarnings("unused")
		Response<Processo> response = new Response<Processo>();
		Page<Processo> processos = processoService.findAllPendentes(paginacao);
		return processos;
	}
	
	
	
	
	@GetMapping
	@Cacheable(value = "findAll")
	public Page<Processo>findAll(@PageableDefault(direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		log.info(""
				+ "busca de processo: {}");
		@SuppressWarnings("unused")
		Response<Processo> response = new Response<Processo>();
		Page<Processo> processos = processoService.findAll(paginacao);
		return processos;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Page<Processo> buscaUser(@PageableDefault(direction = Direction.DESC, page = 0, size = 10) Pageable paginacao,
			@PathVariable("id") Long id) {
		Page<Processo> processo = processoService.findByIdProcesso(paginacao, id);
		return processo;
	}
	
	@PostMapping
	public ResponseEntity<Response<ProcessoDto>> cadastrar(@Valid @RequestBody ProcessoDto processoDto,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Processo: {}",processoDto.toString());
		Response<ProcessoDto> response = new Response<ProcessoDto>();
	
		Processo processos = processoDto.converterProcessoDto(processoDto,result);
	
		if (result.hasErrors()) {
			log.error("Erro validando dados de usuario: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
	
	    final Processo pro = this.processoService.persistir(processos);
	    
		response.setContent(pro.converterProcessos(pro, result));
		return ResponseEntity.ok(response);
	}
	
}
