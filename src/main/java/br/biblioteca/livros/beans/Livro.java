package br.biblioteca.livros.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Livro {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull(message = "NotBlank.java.lang.String")
	@Size(min = 2, max = 100)
	private String nome;
	
	@NotNull()
	@Min(value = 10)
	private int quantidadePaginas;
	
	@OneToMany(mappedBy = "livro")
	private List<Emprestimo> emprestimos = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidadePaginas() {
		return quantidadePaginas;
	}

	public void setQuantidadePaginas(int quantidadePaginas) {
		this.quantidadePaginas = quantidadePaginas;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", nome=" + nome + ", quantidadePaginas=" + quantidadePaginas + "]";
	}
	
	private String capa;

	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}

	@ManyToOne
	@JoinColumn(name = "autor_id")
	private Autor autor;
	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

}
