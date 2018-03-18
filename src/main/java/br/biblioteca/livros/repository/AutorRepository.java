package br.biblioteca.livros.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.biblioteca.livros.beans.Autor;
import br.biblioteca.livros.beans.Usuario;

@Repository
public class AutorRepository {
	@Autowired
	private AutorDao autorDao;
	
	public Autor findByName(String name) {

		Autor autor = null;
		
		for (Autor autorAux : this.findAll()) {
			if (autorAux.getNome().equals(name)) {
				autor = autorAux;
			}
		}

		return autor;
	}
	
	public List<Autor> findAll(){
		return autorDao.findAll();
	}
}
