package br.biblioteca.livros;

import java.util.List;
import java.util.Objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.biblioteca.livros.beans.Autor;
import br.biblioteca.livros.beans.Livro;
import br.biblioteca.livros.repository.AutorDao;
import br.biblioteca.livros.repository.LivroDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LivroTest {
	@Autowired
	private AutorDao autorDao;
	
	@Autowired
	private LivroDao livroDao;
	
	private void exibeInformacoes(Livro livro) {
		System.out.println("Livro: " + String.valueOf(livro.getId()) + " - " + livro.getNome());
		System.out.println("Autor: " + livro.getAutor().getNome());
		System.out.println("Quantidade Páginas: " + String.valueOf(livro.getQuantidadePaginas()));
	}
	
	@Test
	public void insereLivros( ) {
		Autor autor = null;
		
		for (Autor autorAux : autorDao.findAll()) {
			if (autorAux.getNome().equals("Dan Brown")) {
				autor = autorAux;
			}
		}
		
		if (Objects.nonNull(autor)) {
			Livro livro = new Livro();
			
			livro.setNome("Aprendendo JUnit");
			livro.setAutor(autor);
			livro.setQuantidadePaginas(10);
			livroDao.save(livro);
			
			exibeInformacoes(livro);
		}
	}
	
	@Test
	public void alteraLivros() {
		Livro livro = null;
		
		for (Livro livroAux : livroDao.findAll()) {
			if (livroAux.getNome().equals("Aprendendo Java")) {
				livro = livroAux;
			}
		}

		Autor autor = null;
		
		for (Autor autorAux : autorDao.findAll()) {
			if (autorAux.getNome().equals("Dan Brown")) {
				autor = autorAux;
			}
		}
		
		if (Objects.nonNull(livro) && Objects.nonNull(autor)) {
			autor.setNome("Monteiro Lobato");
			autorDao.save(autor);
			
			livro.setNome("Turma da Mônica");
			livro.setAutor(autor);
			livro.setQuantidadePaginas(20);
			livroDao.save(livro);
			
			exibeInformacoes(livro);
		}
	}	
	
	@Test
	public void consultaLivros() {
		List<Livro> livros = livroDao.findAll();
		
		livros.forEach(livro -> {
			exibeInformacoes(livro);
		});
	}
	
	@Test
	public void deletaLivros() {
		Livro livro = null;
		
		for (Livro livroAux : livroDao.findAll()) {
			if (livroAux.getNome().equals("Aprendendo Java")) {
				livro = livroAux;
			}
		}
		
		if (Objects.nonNull(livro)) {
			livroDao.delete(livro);
			
			System.out.println("Exclusão realizada!");
		}
	}
	
	
	
}
