package br.biblioteca.livros.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.biblioteca.livros.beans.Usuario;
import br.biblioteca.livros.services.UsuarioInterface;


@Component
public class UsuarioValidator implements Validator {
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
		if (usuario.getUsername().length() < 3) {
			errors.rejectValue("username", "Size.userForm.username");
		}
		if (usuarioInterface.findByUsername(usuario.getUsername()) != null) {
			errors.rejectValue("username", "Duplicate.userForm.username");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (usuario.getPassword().length() < 3) {
			errors.rejectValue("password", "Size.userForm.password");
		}

		if (!usuario.getPassword().equals(usuario.getPassword())) {
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		}
		
	}
}