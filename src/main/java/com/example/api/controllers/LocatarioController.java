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

import com.example.api.DTOs.LocatarioDTO;
import com.example.api.models.Emprestimo;
import com.example.api.models.Locatario;
import com.example.api.repositories.LocatarioRepository;

@Controller
@RequestMapping("/locatarios")
public class LocatarioController {

	@Autowired
	private final LocatarioRepository repository;
	
 
	String URI = "locatarios/";
	public LocatarioController (LocatarioRepository repository ) {
		this.repository = repository;
 
	}

	@GetMapping("/form")
	public String index(@ModelAttribute Locatario locatario, Model model) {
		model.addAttribute("locatarios", new Locatario());
		String estado = "normal";
		model.addAttribute("estado",estado);
		return URI + "form";
	}
	
	@GetMapping("/view")
	public String view(@ModelAttribute Locatario locatario, Model model) {
		
		Iterable<Locatario> lista = repository.findAll();
		model.addAttribute("locatarios", lista);
		return URI + "view";
	}


	
	@GetMapping(value = "remove/{cpf}")
	public  ModelAndView remove(@PathVariable("cpf") String id,RedirectAttributes redirect) {
		
			
		this.repository.deleteById(id);
		Iterable<Locatario> lista = repository.findAll();
		
		ModelAndView mv = new ModelAndView(URI + "view","locatarios",lista);
		
		return mv;
	}
	
	
	@GetMapping(value = "alter/{cpf}")
	public  ModelAndView alter(@PathVariable("cpf") String id,RedirectAttributes redirect) {
		
		Locatario loc = repository.findById(id).get();
		this.repository.deleteById(id);
		
		ModelAndView mv = new ModelAndView(URI + "form","locatarios",loc);
		
		return mv;
	}

	@PostMapping("/form")
	public String createForm(@ModelAttribute @Valid LocatarioDTO dto, BindingResult result, Model model) {

		if(result.hasErrors()) {
			model.addAttribute("locatarios",dto.toLocatario());
			
			List <String> msg = new ArrayList<String>();
			
			for(ObjectError objectError : result.getAllErrors()) {
				msg.add(objectError.getDefaultMessage()); // VALIDATIONS
			}
			model.addAttribute("msg",msg);
			return "locatarios/form";
		}
		String msg = "Locatário registrado com sucesso!";
		repository.save(dto.toLocatario());
		String estado = "normal";
		model.addAttribute("estado",estado);
		model.addAttribute("locatarios", new Locatario());
		model.addAttribute("msg",msg);
		return "locatarios/form";
	}
	
	@GetMapping(value = "render/{id}")
	public String renderLocatarios(@PathVariable("id") String id, Model model) {

		Optional<Locatario> est = repository.findById(id);
		List<Emprestimo> lista = est.
				get().
				getEmprestimos();
			 

		model.addAttribute("emprestimos",lista);
		return URI + "renderemprestimos";
	}
}
