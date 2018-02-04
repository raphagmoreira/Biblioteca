package br.biblioteca.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.biblioteca.livros.beans.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {

}
