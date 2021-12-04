package com.example.api.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.api.DTOs.LivroDTO;
import com.example.api.models.Emprestimo;
import com.example.api.models.Livro;
import com.example.api.repositories.LivroRepository;

@Controller
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private final LivroRepository repository;
	String URI = "livros/";
	public LivroController (LivroRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/form")
	public String index(@ModelAttribute Livro livro, Model model) {
		model.addAttribute("livros", new Livro());
		String estado = "normal";
		model.addAttribute("estado",estado);
		return URI + "form";
	}

	@GetMapping("/view")
	public String view(@ModelAttribute Livro livro, Model model) {

		Iterable<Livro> lista = repository.findAll();
		model.addAttribute("livros", lista);
		return URI + "view";
	}

	@PostMapping("/form")
	public String createForm( @ModelAttribute @Valid LivroDTO dto, BindingResult result, Model model) {

		if(result.hasErrors()) {

			model.addAttribute("livros", dto.toLivro());

			List <String> msg = new ArrayList<String>();
			for(ObjectError objectError : result.getAllErrors()) {
				msg.add(objectError.getDefaultMessage()); // @NotEmpty & @NotNull

			}
			String estado = "normal";
			model.addAttribute("estado",estado);
			model.addAttribute("msg", msg);
			System.out.println(msg);

			return "livros/form";
		}

		String msg = "Livro registrado com sucesso!";
		model.addAttribute("msg", msg);
		Livro livro = dto.toLivro();
		livro.setDisponibilidade("Disponível");
		repository.save(livro);
		String estado = "normal";
		model.addAttribute("estado",estado);
		model.addAttribute("livros", new Livro());

		return "livros/form";
	}
	@GetMapping(value = "remove/{id}")
	public  ModelAndView remove(@PathVariable("id") long id) {


		this.repository.deleteById(id);
		Iterable<Livro> lista = repository.findAll();

		ModelAndView mv = new ModelAndView(URI + "view","livros",lista);

		return mv;
	}

	@GetMapping(value = "alter/{id}")
	public  ModelAndView alter(@PathVariable("id") long id) {

		Livro livro = repository.findById(id).get();	
		this.repository.deleteById(id);
		Iterable<Livro> lista = repository.findAll();

		ModelAndView mv = new ModelAndView(URI + "form","livros",livro);

		return mv;
	}
}
