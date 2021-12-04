package com.example.api.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.example.api.DTOs.EmprestimoDTO;
import com.example.api.models.Emprestimo;
import com.example.api.models.Livro;
import com.example.api.repositories.EmprestimoRepository;
import com.example.api.repositories.LivroRepository;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

	@Autowired
	private final EmprestimoRepository repository;
	
	@Autowired
	private final LivroRepository livroRepository;

	String URI = "emprestimos/";
	public EmprestimoController (EmprestimoRepository repository,LivroRepository livroRepository ) {
		this.repository = repository;
		this.livroRepository = livroRepository;
	}

	@GetMapping("/form")
	public String index(@ModelAttribute Emprestimo emprestimo, Model model) {
		
		List<Livro> listaLivros = livroRepository.findAll();
		
		model.addAttribute("emprestimos", new Emprestimo());
		String estado = "normal";
		model.addAttribute("livros",listaLivros);
		model.addAttribute("estado",estado);

		return URI + "form";
	}

	@PostMapping("/form")
	public String createForm(@ModelAttribute @Valid EmprestimoDTO dto, BindingResult result, Model model) {
		
		
		if(result.hasErrors()) {
		
		model.addAttribute("emprestimos", new Emprestimo());
		
		List <String> msg = new ArrayList<String>();
		
		for(ObjectError objectError : result.getAllErrors()) {
			msg.add(objectError.getDefaultMessage()); // VALIDATIONS
		}
		
		String estado = "normal";
		model.addAttribute("estado",estado);
		model.addAttribute("msg",msg);
		return "emprestimos/form";
				}

		
		
		repository.save(dto.toEmprestimo());
	
Optional<Livro> lib =		livroRepository.
		findById(dto.
				toEmprestimo().
				getLivro().
				getID()); 

	Livro livro = lib.get();
	livro.setDisponibilidade("Indisponível");
	livroRepository.saveAndFlush(livro);
		
		String msg = "Empréstimo registrado com sucesso!";
		
		model.addAttribute("emprestimos", dto.toEmprestimo());
		
		model.addAttribute("msg",msg);
	

		return "emprestimos/form";
	}
	
	@GetMapping("/view")
	public String view(@ModelAttribute Emprestimo emprestimo, Model model) {
		
		Iterable<Emprestimo> lista = repository.findAll();
		model.addAttribute("emprestimos", lista);
		return URI + "view";
	}

	
	@GetMapping(value = "remove/{id}")
	public  ModelAndView remove(@PathVariable("id") long id,RedirectAttributes redirect) {
		
			
		this.repository.deleteById(id);
		Iterable<Emprestimo> lista = repository.findAll();
		
		ModelAndView mv = new ModelAndView(URI + "view","emprestimos",lista);
		
		return mv;
	}

	@GetMapping(value = "alter/{id}")
	public  ModelAndView alter(@PathVariable("id") long id) {
		
		Emprestimo emp = repository.findById(id).get();
		repository.deleteById(id);
		ModelAndView mv = new ModelAndView(URI + "form","emprestimos", emp);
		
		return mv;
	}

}
