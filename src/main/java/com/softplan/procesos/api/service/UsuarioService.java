package com.softplan.procesos.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softplan.procesos.api.repositories.UsuarioRepository;
import com.softplan.procesos.api.entity.Usuario;

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

	public Optional<Usuario> buscarPorEmail(String email) {
		return this.usuarioRepository.findByEmail(email);
	}

	public void remover(Long id) {
		this.usuarioRepository.deleteById(id);;
		
	}


}
