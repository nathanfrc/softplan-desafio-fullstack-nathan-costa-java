package com.softplan.processos.api.dto;


import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softplan.processos.api.entity.Processo;

public class ProcessoDto {
	
	private Long id;
	private String titulo;
	private String descricao;
	

	public ProcessoDto() {
	}

	

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date dataCriacao;
	
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message = "Titulo não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Titulo deve conter entre 3 e 200 caracteres.")
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	
	@NotEmpty(message = "Descricao não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Descricao deve conter entre 3 e 200 caracteres.")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Processo converterProcessoDto(ProcessoDto processoDto, BindingResult result) {
		Processo processo = new Processo();
		processo.setTitulo(processoDto.getTitulo());
		processo.setDescricao(processoDto.getDescricao());
		processo.setDataCriacao(new Date());
		
		return processo;
	}

	

	

/*public Usuario converterUsuarioDto(ProcessoDto usuarioDto, BindingResult result) {
		
		Usuario usuario = new Usuario();
		usuario.setId(usuarioDto.getId());
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setNome(usuarioDto.getNome());
		
		if(usuarioDto.getSenha() != null){
			usuario.setSenha(PasswordUtils.gerarBCrypt(usuarioDto.getSenha()));
		}
		
		usuario.setPerfil(usuarioDto.getPerfil());
		usuario.setDataCriacao( new Date());
	
		return usuario; 
	}*/

}
