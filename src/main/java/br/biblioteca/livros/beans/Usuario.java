package br.biblioteca.livros.beans;

import java.util.ArrayList;
import java.util.List;

import br.biblioteca.livros.beans.Role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull(message = "NotBlank.java.lang.String")
	@Size(min = 2, max = 100)
	private String username;

	@NotNull(message = "NotBlank.java.lang.String")
	@Size(max = 2000)
	private String password;
	
	public Usuario() { }

	public Usuario(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@OneToMany(mappedBy = "usuario")
	private List<Role> roles = new ArrayList<>();
	
	@OneToMany(mappedBy = "usuarioEmprestimo")
	private List<Emprestimo> emprestimos = new ArrayList<>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}
}
