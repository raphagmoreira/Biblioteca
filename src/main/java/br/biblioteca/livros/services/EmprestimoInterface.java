package br.biblioteca.livros.services;

import java.util.List;

import br.biblioteca.livros.beans.Emprestimo;

public interface EmprestimoInterface {
	void save(Emprestimo emprestimo);
	
	List<Emprestimo> findAll();
}