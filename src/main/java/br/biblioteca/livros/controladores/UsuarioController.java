package br.biblioteca.livros.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.beans.Usuario;
import br.biblioteca.livros.repository.UsuarioDao;
import br.biblioteca.livros.services.SecurityService;
import br.biblioteca.livros.services.UsuarioInterface;
import br.biblioteca.livros.services.UsuarioServiceImpl;
import br.biblioteca.livros.validator.LoginValidator;
import br.biblioteca.livros.validator.UsuarioValidator;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UsuarioController {

	@Autowired
	private UsuarioInterface usuarioService;
	@Autowired
	private UsuarioValidator usuarioValidator;
	@Autowired
	private LoginValidator loginValidator;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;

	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("usuarios/form", "userForm", new Usuario());
	}

	@PostMapping("/autentication")
	public ModelAndView autentication(@ModelAttribute("userForm") Usuario userForm, BindingResult bindingResult, Model model) {
	//public ModelAndView autentication(String usuario, String senha, BindingResult bindingResult, Model model) {
		//Usuario userForm = new Usuario(usuario, senha);
		
		loginValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return new ModelAndView("redirect:/user/login");
		}
		
		securityService.login(userForm.getUsername(), userForm.getPassword());
		return new ModelAndView("redirect:/user/list");
	}

	@GetMapping("/list")
	public ModelAndView list() {
		return new ModelAndView("/usuarios/list");
	}
	
	@GetMapping("/listadmin")
	public ModelAndView listadmin(Usuario user) {

		List<Usuario> usuarios = usuarioService.findAll();
		return new ModelAndView("/usuarios/listadmin","users",usuarios);
	}

	@GetMapping(value = "/registration")
	public ModelAndView registration() {
		return new ModelAndView("usuarios/registration", "userForm", new Usuario());
	}

	@PostMapping(value = "/registration")
	public ModelAndView registrationform(@ModelAttribute("userForm") Usuario userForm, BindingResult bindingResult, Model model) {

		usuarioValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return new ModelAndView("usuarios/registration");
		}
		
		String password = userForm.getPassword();

		usuarioServiceImpl.save(userForm);

		try {
			securityService.login(userForm.getUsername(), password);
			return new ModelAndView("redirect:/user/list");
			
		} catch (Exception e) {
			
			return new ModelAndView("redirect:/user/login");
		}
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		
		if (session != null) {
			session.invalidate();
		}
	     
		return new ModelAndView("redirect:/user/login");
	}

}