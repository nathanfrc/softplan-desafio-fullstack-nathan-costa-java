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
import com.softplan.procesos.api.entity.ProcessoUsuario;
import com.softplan.procesos.api.entity.Usuario;
import com.softplan.procesos.api.response.Response;
import com.softplan.procesos.api.service.ParecerService;
import com.softplan.procesos.api.service.ProcessoService;

@RestController
@RequestMapping("/api/parecer")
@CrossOrigin(origins = "*")
public class ParecerController {

	private static final Logger log = LoggerFactory.getLogger(ParecerController.class);
	
    @Autowired 
	private ParecerService parecerService;
    
   
	public ParecerController() {
	}
	
	/*@GetMapping
	@Cacheable(value = "findAll")
	public Page<ProcessoUsuario>findAllPendentes(@PageableDefault(direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		log.info(""
				+ "busca de processo: {}");
		@SuppressWarnings("unused")
		Response<Processo> response = new Response<Processo>();
		//Page<ProcessoUsuario> processos = parecerService.findAll(paginacao);
		
		
		Page<ProcessoUsuario> processos;
		return processos;
	}*/
	
	
	
}
