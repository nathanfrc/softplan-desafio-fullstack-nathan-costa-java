package com.softplan.procesos.api.dto;


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
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softplan.procesos.api.entity.Usuario;
import com.softplan.procesos.api.enums.PerfilEnum;
import com.softplan.procesos.api.utils.PasswordUtils;

public class UsuarioDto {
	
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private String perfil;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date dataCriacao;
	
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public UsuarioDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message = "Nome não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotEmpty(message = "Email não pode ser vazio.")
	@Length(min = 5, max = 200, message = "Email deve conter entre 5 e 200 caracteres.")
	@Email(message="Email inválido.")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "UsuarioDto [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", perfil="
				+ perfil + "]";
	}
	
	

public Usuario converterUsuarioDto(UsuarioDto usuarioDto, BindingResult result) {
		
		Usuario usuario = new Usuario();
		usuario.setId(usuarioDto.getId());
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setNome(usuarioDto.getNome());
		
		if(usuarioDto.getSenha() != null){
			usuario.setSenha(PasswordUtils.gerarBCrypt(usuarioDto.getSenha()));
		}
		
		usuario.setPerfil(usuarioDto.getPerfil().toString());
		usuario.setDataCriacao( new Date());
	
		return usuario; 
	}

@SuppressWarnings("unused")
public static Usuario mergeObjetos(UsuarioDto usuarioDto,Usuario usuario ) {
	
	final Usuario usuarioFinal = new Usuario();
	
	usuarioFinal.setId(usuario.getId());
	
	if(usuarioDto.getEmail()!=null) {
		usuarioFinal.setEmail(usuarioDto.getEmail());
	}else {
		usuarioFinal.setEmail(usuario.getEmail());
	}
	
	if(usuarioDto.getNome()!=null) {
		usuarioFinal.setNome(usuarioDto.getNome());
	}else {
		usuarioFinal.setNome(usuario.getNome());
	}
	
	if(usuarioDto.getSenha()!=null) {
		
		usuarioFinal.setSenha(PasswordUtils.gerarBCrypt(usuarioDto.getSenha()));
		
	}else {
		usuarioFinal.setSenha(usuario.getSenha());
	}
	
	if(usuarioDto.getPerfil()!=null) {
		usuarioFinal.setPerfil(usuarioDto.getPerfil()); 
	}else {
		usuarioFinal.setPerfil(usuario.getPerfil());
	}
	
	if(usuarioDto.getDataCriacao()!=null) {
		usuarioFinal.setDataCriacao(usuarioDto.getDataCriacao()); 
	}else {
		usuarioFinal.setDataCriacao(usuario.getDataCriacao());
	}
	
	return usuarioFinal;
	
}

}
