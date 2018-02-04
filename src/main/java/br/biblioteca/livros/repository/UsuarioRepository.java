package br.biblioteca.livros.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.biblioteca.livros.beans.Role;
import br.biblioteca.livros.beans.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {
	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private RoleDao roleDao;
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	UsuarioRepository() {
		
		/*Usuario basic = new Usuario("teste",passwordEncoder.encode("123456"));
		basic.getRoles().add(new Role("ROLE_BASIC"));
		usuarios.add(basic);
		
		Usuario admin = new Usuario("admin", passwordEncoder.encode("123456"));
		admin.getRoles().add(new Role("ROLE_BASIC"));
		admin.getRoles().add(new Role("ROLE_ADMIN"));
		usuarios.add(admin);*/
		
		//usuarios = usuarioDao.findAll();
		
	}

	public Usuario findByUsername(String username) {

		Usuario usuario = null;
		
		for (Usuario u : this.findAll()) {

			if (u.getUsername().equals(username)) {
				usuario = u;
			}

		}
		
		//System.out.println("usuário encontrado: " + usuario.getUsername());

		return usuario;
	}
	
	public Usuario findById(Long id) {
		return usuarioDao.findOne(id);
	}

	public void save(Usuario usuario, Role role) {
		usuarioDao.save(usuario);
		roleDao.save(role);
	}


	public List<Usuario> findAll(){
		return usuarioDao.findAll();
	}
}
