package br.biblioteca.livros.services;

import java.util.List;

import br.biblioteca.livros.beans.Usuario;

public interface UsuarioInterface {
	void save(Usuario usuario);

	Usuario findByUsername(String username);

	List<Usuario> findAll();
}
