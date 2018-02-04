package br.biblioteca.livros.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Emprestimo {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull()
	@ManyToOne
	@JoinColumn(name = "livro_id")
	private Livro livro;
	
	@NotNull()
	private Calendar dataEmprestimo;
	
	private String dataDevolucao;
	
	private String dataEntrega;
	
	@NotNull()
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuarioEmprestimo;
	
	private String dataEmprestimoFormatada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Calendar getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Calendar dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public String getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Usuario getUsuarioEmprestimo() {
		return usuarioEmprestimo;
	}

	public void setUsuarioEmprestimo(Usuario usuarioEmprestimo) {
		this.usuarioEmprestimo = usuarioEmprestimo;
	}
	
	public String getDataEmprestimoFormatada() {
		final DateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dataFormat.format(this.getDataEmprestimo().getTime());
	}
}
