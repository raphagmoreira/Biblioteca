package br.biblioteca.livros.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.biblioteca.livros.beans.Role;
import br.biblioteca.livros.beans.Usuario;
import br.biblioteca.livros.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioInterface {
	@Autowired
    private UsuarioRepository usuarioRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void save(Usuario usuario) {
    	Role role = new Role("ROLE_BASIC");
    	
    	usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
    	role.setUsuario(usuario);
    	
    	usuarioRepository.save(usuario, role);
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);

    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
}
