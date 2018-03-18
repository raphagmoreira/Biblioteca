package br.biblioteca.livros;

import java.util.List;
import java.util.Objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.biblioteca.livros.beans.Usuario;
import br.biblioteca.livros.repository.UsuarioDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioTest {
	@Autowired
	private UsuarioDao usuarioDao;
	
	private void exibeInformacoes(Usuario usuario) {
		System.out.println("Usuário: " + String.valueOf(usuario.getId()) + " - " + usuario.getUsername());
		System.out.println("Senha: " + usuario.getPassword());
	}
	
	@Test
	public void insereUsuario( ) {
		Usuario usuario = new Usuario();
		
		usuario.setUsername("Teste");
		usuario.setPassword("123456");
		usuarioDao.save(usuario);
		
		exibeInformacoes(usuario);
	}
	
	@Test
	public void alteraUsuario() {		
		Usuario usuario = null;
		
		for (Usuario usuarioAux : usuarioDao.findAll()) {
			if (usuarioAux.getUsername().equals("admin")) {
				usuario = usuarioAux;
			}
		}
		
		if (Objects.nonNull(usuario)) {
			usuario.setUsername("Teste");
			usuario.setPassword("123456");
			usuarioDao.save(usuario);
			
			exibeInformacoes(usuario);
		}
	}	
	
	@Test
	public void consultaUsuario() {
		List<Usuario> usuarios = usuarioDao.findAll();
		
		usuarios.forEach(usuario -> {
			exibeInformacoes(usuario);
		});
	}
	
	@Test
	public void deletaUsuario() {
		Usuario usuario = null;
		
		for (Usuario usuarioAux : usuarioDao.findAll()) {
			if (usuarioAux.getUsername().equals("admin")) {
				usuario = usuarioAux;
			}
		}
		
		if (Objects.nonNull(usuario)) {
			usuarioDao.delete(usuario);
			
			System.out.println("Exclusão realizada!");
		}
	}
}
