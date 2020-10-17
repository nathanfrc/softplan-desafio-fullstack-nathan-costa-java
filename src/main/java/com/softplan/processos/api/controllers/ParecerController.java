package com.softplan.processos.api.controllers;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softplan.processos.api.dto.ParecerDto;
import com.softplan.processos.api.entity.Parecer;
import com.softplan.processos.api.responses.Response;
import com.softplan.processos.api.service.ParecerService;
import com.softplan.processos.api.dto.ProcessoDto;
import com.softplan.processos.api.entity.Processo;
import com.softplan.processos.api.entity.ProcessoUsuario;
import com.softplan.processos.api.entity.Usuario;
import com.softplan.processos.api.service.ProcessoService;
import com.softplan.processos.api.service.UsuarioService;

@RestController
@RequestMapping("/api/parecer")
@CrossOrigin(origins = "*")
public class ParecerController {

	private static final Logger log = LoggerFactory.getLogger(ParecerController.class);
	
    @Autowired 
	private ParecerService parecerService;
    
    @Autowired 
	private ProcessoService processoService;
    
    @Autowired
	private UsuarioService usuarioService;
    
	public ParecerController() {
	}
	

	@PostMapping(value="/usuario/{idUsuario}/processo/{idProcesso}")
	public ResponseEntity<Response<ParecerDto>> cadastrar(@Valid @RequestBody ParecerDto parecerDto,
			BindingResult result, @PathVariable("idProcesso") Long idProcesso, @PathVariable("idUsuario") Long idUsuario) throws NoSuchAlgorithmException {
		log.info("Cadastrando Psrecer: {}",parecerDto.toString());
		Response<ParecerDto> response = new Response<ParecerDto>();
		
		log.info("log parecerDto"+parecerDto.toString());
		
		Parecer parecer = parecerDto.converterParecerDto(parecerDto,result);
		
		log.info("log parecerDto"+parecer.toString());
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de usuario: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Optional<Usuario> usOptional = this.usuarioService.findById(idUsuario);

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
		
		parecer.setUsuario(usOptional.get());
		parecer.setProcesso(processoOptional.get());
		
		
		
		this.parecerService.salvar(parecer);
		
		return ResponseEntity.ok().build();
	}
	
	

	
}
