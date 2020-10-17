package com.softplan.processos.api.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.softplan.processos.api.dto.ProcessoDto;

@Entity
@Table(name = "processos")
public class Processo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String titulo;
	private String descricao;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date dataCriacao;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "processos_id", referencedColumnName = "id")
	private Set<Parecer> parecer;
	
	  @JsonIgnore
	  @OneToMany(mappedBy = "processo")
      Set<ProcessoUsuario> processoUsuario;
	
	public Set<ProcessoUsuario> getProcessoUsuario() {
		return processoUsuario;
	}

	public void setProcessoUsuario(Set<ProcessoUsuario> processoUsuario) {
		this.processoUsuario = processoUsuario;
	}

	public Set<Parecer> getParecer() {
		return parecer;
	}

	public void setParecer(Set<Parecer> parecer) {
		this.parecer = parecer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Processo() {
	}

	public ProcessoDto converterProcessos(Processo pro, BindingResult result) {

		ProcessoDto processoDto = new ProcessoDto();

		processoDto.setId(pro.getId());
		processoDto.setTitulo(pro.getTitulo());
		processoDto.setDescricao(pro.getDescricao());
		processoDto.setDataCriacao((pro.dataCriacao));

		return processoDto;
	}
}
