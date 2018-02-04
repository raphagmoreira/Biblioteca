package br.biblioteca.livros.controladores;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.beans.Autor;
import br.biblioteca.livros.beans.Emprestimo;
import br.biblioteca.livros.beans.Livro;
import br.biblioteca.livros.beans.Usuario;
import br.biblioteca.livros.repository.EmprestimoRepository;
import br.biblioteca.livros.repository.LivroDao;
import br.biblioteca.livros.repository.UsuarioRepository;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {
	
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private LivroDao livroDao;
	
	@GetMapping("/list")
	public ModelAndView emprestimos() {
		Iterable<Emprestimo> emprestimos = emprestimoRepository.findAll();
		return new ModelAndView("emprestimos/list", "emprestimos", emprestimos);
	}
	
	@GetMapping("/novo")
	public ModelAndView createForm(@ModelAttribute Emprestimo emprestimo) {
		ModelAndView modelAndView = new ModelAndView("emprestimos/form");	
		
		List<Usuario> usuarios = usuarioRepository.findAll();
		modelAndView.addObject("usuarios", usuarios);	
		
		List<Livro> livros = livroDao.findAll();
		modelAndView.addObject("livros", livros);
		
		return modelAndView;
	}
	
	@PostMapping(params = "form")
	public ModelAndView create(@ModelAttribute("emprestimo") @Valid Emprestimo emprestimo, BindingResult bindingResult, Model model) {
		emprestimo.setDataEmprestimo(Calendar.getInstance());
		emprestimoRepository.save(emprestimo);
		
	    return new ModelAndView("redirect:/emprestimos/list");
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		Emprestimo emprestimo = this.emprestimoRepository.findById(id);
		List<Usuario> usuarios = usuarioRepository.findAll();		
		List<Livro> livros = livroDao.findAll();
		
		ModelAndView modelAndView = new ModelAndView("emprestimos/form");
		
		modelAndView.addObject("usuarios", usuarios);
		modelAndView.addObject("livros", livros);
		modelAndView.addObject("emprestimo", emprestimo);
		
		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		Emprestimo emprestimo = this.emprestimoRepository.findById(id);
		
		this.emprestimoRepository.delete(emprestimo);
		return new ModelAndView("redirect:/emprestimos/list");
	}
	
}
