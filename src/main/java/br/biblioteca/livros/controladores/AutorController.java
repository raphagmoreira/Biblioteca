package br.biblioteca.livros.controladores;

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
import br.biblioteca.livros.repository.AutorDao;

@Controller
@RequestMapping("/autores")
public class AutorController {
	
	@Autowired
	private AutorDao autorRepository;
	
	@GetMapping("/list")
	public ModelAndView autores() {
		Iterable<Autor> autores = autorRepository.findAll();
		return new ModelAndView("autores/list", "autores", autores);
	}
	
	@GetMapping("/novo")
	public ModelAndView createForm(@ModelAttribute Autor autor) {
		ModelAndView modelAndView = new ModelAndView("autores/form");
		return modelAndView;
	}
	
	@PostMapping(params = "form")
	public ModelAndView create(@ModelAttribute("autor") @Valid Autor autor, BindingResult bindingResult, Model model) {
		ModelAndView modelAndView = new ModelAndView("autores/form");
		
		if(autor.getNome().equals("")) {
			model.addAttribute("message", "Nome esta em branco");
			return modelAndView;
		}
		
		if (bindingResult.hasErrors()  || model.containsAttribute("nome")) {
			return modelAndView;
		}
		
		autor = this.autorRepository.save(autor);
	    return new ModelAndView("redirect:/autores/list");
	}
 

	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		Autor autor = this.autorRepository.findOne(id);
		
		ModelAndView modelAndView = new ModelAndView("autores/form");
		modelAndView.addObject("autor", autor);
		
		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		Autor autor = this.autorRepository.findOne(id);
		this.autorRepository.delete(autor);
		return new ModelAndView("redirect:/autores/list");
	}

}
