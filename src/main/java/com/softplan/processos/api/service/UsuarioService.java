package com.softplan.processos.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softplan.processos.api.entity.Usuario;
import com.softplan.processos.api.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Page<Usuario> findAll(Pageable paginacao) {
		return this.usuarioRepository.findAll(paginacao); 
	}

	public Optional<Usuario> findById( Long id) {
		return this.usuarioRepository.findById(id);
	}

	public Usuario persistir(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}

	public Page<Usuario> findByIdUsuario(Pageable paginacao, Long id) {
		return this.usuarioRepository.findById(id,paginacao);
	}

	public Page<Usuario> buscarPorEmailPage(String email,Pageable paginacao) {
		return this.usuarioRepository.findByEmailPage(email,paginacao);
	}
	
	public Optional<Usuario> buscarPorEmail(String email) {
		return this.usuarioRepository.findByEmail(email);
	}

	public void remover(Long id) {
		this.usuarioRepository.deleteById(id);;
		
	}

	public List<Usuario> findAll2() {
		return this.usuarioRepository.findAll();
	}


}
