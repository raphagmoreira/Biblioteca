package br.biblioteca.livros.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.biblioteca.livros.beans.Usuario;
import br.biblioteca.livros.services.UsuarioInterface;


@Component
public class LoginValidator implements Validator {
	
	@Autowired
	private UsuarioInterface usuarioInterface;

	@Override
	public boolean supports(Class<?> aClass) {
		return Usuario.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		
		Usuario usuario = (Usuario) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		
		if (usuarioInterface.findByUsername(usuario.getUsername()) == null) {
			errors.rejectValue("username", "NotExist.userForm.username");
		}
		
	}
}