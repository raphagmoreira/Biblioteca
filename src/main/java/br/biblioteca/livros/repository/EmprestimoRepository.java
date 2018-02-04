package br.biblioteca.livros.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.biblioteca.livros.beans.Emprestimo;
import br.biblioteca.livros.beans.Usuario;

@Repository
public class EmprestimoRepository {
	
	@Autowired
	private EmprestimoDao emprestimoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;

	public List<Emprestimo> findAll(){
		List<Emprestimo> emprestimos = emprestimoDao.findAll();		
		return emprestimos;
	}
	
	public Emprestimo findById(Long id) {
		return emprestimoDao.findOne(id);
	}
	
	public void save(Emprestimo emprestimo) {
		emprestimoDao.save(emprestimo);
	}
	
	public void delete(Emprestimo emprestimo) {
		emprestimoDao.delete(emprestimo);
	}
}
