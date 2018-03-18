package br.biblioteca.livros;

import java.util.List;
import java.util.Objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.biblioteca.livros.beans.Autor;
import br.biblioteca.livros.repository.AutorDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutorTest {
	@Autowired
	private AutorDao autorDao;
	
	private void exibeInformacoes(Autor autor) {
		System.out.println("Autor: " + String.valueOf(autor.getId()) + " - " + autor.getNome());
	}
	
	@Test
	public void insereAutor( ) {
		Autor autor = new Autor();
		
		autor.setNome("Cecília Meireles");
		autorDao.save(autor);
		
		exibeInformacoes(autor);
	}
	
	@Test
	public void alteraAutor() {		
		Autor autor = null;
		
		for (Autor autorAux : autorDao.findAll()) {
			if (autorAux.getNome().equals("Dan Brown")) {
				autor = autorAux;
			}
		}
		
		if (Objects.nonNull(autor)) {
			autor.setNome("Monteiro Lobato");
			autorDao.save(autor);
			
			exibeInformacoes(autor);
		}
	}	
	
	@Test
	public void consultaAutor() {
		List<Autor> autores = autorDao.findAll();
		
		autores.forEach(autor -> {
			exibeInformacoes(autor);
		});
	}
	
	@Test
	public void deletaAutor() {
		Autor autor = null;
		
		for (Autor autorAux : autorDao.findAll()) {
			if (autorAux.getNome().equals("Monteiro Lobato")) {
				autor = autorAux;
			}
		}
		
		if (Objects.nonNull(autor)) {
			autorDao.delete(autor);
			
			System.out.println("Exclusão realizada!");
		}
	}
}
