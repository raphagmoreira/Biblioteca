package br.biblioteca.livros;

import org.springframework.beans.factory.annotation.Autowired;

import br.biblioteca.livros.beans.Livro;
import br.biblioteca.livros.repository.AutorRepository;
import br.biblioteca.livros.repository.LivroDao;

public class CriadorLivro {
	private String titulo;
	private String autor;
	private Integer quantidade;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private LivroDao livroDao;
	
	public CriadorLivro livro(String titulo) {
		this.titulo = titulo;
		return this;
	}
	
	public CriadorLivro autor(String autor) {
		this.autor = autor;
		return this;
	}
	
	public CriadorLivro quantidade(Integer quantidade) {
		this.quantidade = quantidade;
		return this;
	}
	
	public Livro constroi() {
		Livro livro = new Livro();
		
		livro.setNome(titulo);
		livro.setQuantidadePaginas(quantidade);
		livro.setAutor(autorRepository.findByName(autor));
		livroDao.save(livro);
		
		return livro;
	}
}
