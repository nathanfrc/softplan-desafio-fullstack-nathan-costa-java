package com.softplan.procesos.api.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softplan.procesos.api.dto.UsuarioDto;
import com.softplan.procesos.api.enums.PerfilEnum;


@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	@Column(unique=true)
	private String email;
	private String senha;
	private String perfil;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date dataCriacao;
	
	@JsonIgnore
	 @ManyToMany
	    @JoinTable(
	         name="processos_usuarios",
	         joinColumns        = @JoinColumn(name = "usuarios_id"), 
	      	 inverseJoinColumns = @JoinColumn(name = "processos_id")
	    )
	     private List<Processo> processos;
	 
	    @JsonIgnore
	    @OneToMany(cascade = CascadeType.ALL)
		@JoinColumn( name = "usuarios_id", referencedColumnName = "id")
		private List<Parecer> parecer;
	 

	public List<Parecer> getParecer() {
			return parecer;
		}

		public void setParecer(List<Parecer> parecer) {
			this.parecer = parecer;
		}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	@Column(name = "senha", nullable = false)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	

	@Column(name = "perfil", nullable = false)
	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@Column(name = "data_criacao", nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public List<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

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
