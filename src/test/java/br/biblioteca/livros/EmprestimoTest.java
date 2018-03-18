package br.biblioteca.livros;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.biblioteca.livros.beans.Emprestimo;
import br.biblioteca.livros.beans.Livro;
import br.biblioteca.livros.beans.Usuario;
import br.biblioteca.livros.repository.EmprestimoDao;
import br.biblioteca.livros.repository.LivroDao;
import br.biblioteca.livros.repository.UsuarioDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmprestimoTest {
	@Autowired
	private EmprestimoDao emprestimoDao;
	@Autowired
	private LivroDao livroDao;
	@Autowired
	private UsuarioDao usuarioDao;
	
	private void exibeInformacoes(Emprestimo emprestimo) {
		System.out.println("Data Emprestimo: " + String.valueOf(emprestimo.getDataEmprestimo().getTime()));
		System.out.println("Livro Emprestado: " + emprestimo.getLivro().getNome());
		System.out.println("Usuário Empreśtimo: " + emprestimo.getUsuarioEmprestimo().getUsername());
	}
	
	@Test
	public void insereEmprestimo( ) {
		Livro livro = null;
		
		for (Livro livroAux : livroDao.findAll()) {
			if (livroAux.getNome().equals("Aprendendo Java")) {
				livro = livroAux;
			}
		}
		
		Usuario usuario = null;
		
		for (Usuario usuarioAux : usuarioDao.findAll()) {
			if (usuarioAux.getUsername().equals("admin")) {
				usuario = usuarioAux;
			}
		}
		
		if (Objects.nonNull(livro) && Objects.nonNull(usuario)) {
			Emprestimo emprestimo = new Emprestimo();
			
			emprestimo.setDataEmprestimo(Calendar.getInstance());
			emprestimo.setLivro(livro);
			emprestimo.setUsuarioEmprestimo(usuario);
			emprestimoDao.save(emprestimo);
			
			exibeInformacoes(emprestimo);
		}
	}
	
	@Test
	public void alteraEmprestimo() {
		Livro livro = null;
		
		for (Livro livroAux : livroDao.findAll()) {
			if (livroAux.getNome().equals("Uma mente brilhante")) {
				livro = livroAux;
			}
		}
		
		Emprestimo emprestimo = emprestimoDao.findOne(1L);
		
		if (Objects.nonNull(emprestimo) && Objects.nonNull(livro)) {
			emprestimo.setLivro(livro);
			emprestimoDao.save(emprestimo);
			
			exibeInformacoes(emprestimo);
		}
	}	
	
	@Test
	public void consultaEmprestimo() {
		List<Emprestimo> emprestimos = emprestimoDao.findAll();
		
		emprestimos.forEach(emprestimo -> {
			exibeInformacoes(emprestimo);
		});
	}
	
	@Test
	public void deletaEmprestimo() {
		Emprestimo emprestimo = emprestimoDao.findOne(1L);
		
		if (Objects.nonNull(emprestimo)) {
			emprestimoDao.delete(emprestimo);
			
			System.out.println("Exclusão realizada!");
		}
	}
}
