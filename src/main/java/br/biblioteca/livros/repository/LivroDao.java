package br.biblioteca.livros.repository;

import br.biblioteca.livros.beans.Livro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroDao  extends JpaRepository <Livro, Long> { }
