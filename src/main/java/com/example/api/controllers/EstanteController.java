package com.example.api.controllers;

import java.net.BindException;
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

import com.example.api.DTOs.EstanteDTO;
import com.example.api.models.Estante;
import com.example.api.models.Livro;
import com.example.api.repositories.EstanteRepository;

@Controller
@RequestMapping("/estantes")
public class EstanteController {

	@Autowired
	private final EstanteRepository repository;
	String URI = "estantes/";

	public EstanteController (EstanteRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/form")
	public String create(@ModelAttribute Estante estante, Model model) {
		model.addAttribute("estantes", new Estante());
		String estado = "normal";
		model.addAttribute("estado",estado);
		return URI + "form";
	}

	@PostMapping("/form")
	public String create(@ModelAttribute @Valid EstanteDTO dto, BindingResult result, Model model) throws BindException {

		if(result.hasErrors()) {

			model.addAttribute("estantes",dto.toEstante());

			List <String> msg = new ArrayList<String>();
			for(ObjectError objectError : result.getAllErrors()) {
				msg.add(objectError.getDefaultMessage()); // @NotEmpty & @NotNull

			}
			model.addAttribute("msg",msg);

			System.out.println(msg);

			return "estantes/form";
		}
		repository.save(dto.toEstante());

		String msg = "Estante registrada com sucesso!";
		String estado = "normal";
		model.addAttribute("estado",estado);
		model.addAttribute("msg",msg);
		model.addAttribute("estantes",new Estante());
		System.out.println(result.getAllErrors());
		return "estantes/form";
	}

	@GetMapping("/view")
	public String view(Model model) {

		List<Estante> lista = repository.findAll();
		model.addAttribute("estantes",lista);
		return URI + "view";
	}

	@GetMapping(value = "remove/{id}")
	public  ModelAndView remove(@PathVariable("id") long id) {


		this.repository.deleteById(id);
		Iterable<Estante> lista = repository.findAll();

		ModelAndView mv = new ModelAndView(URI + "view","estantes",lista);

		return mv;
	}

	@GetMapping(value = "alter/{id}")
	public  ModelAndView alter(@PathVariable("id") long id) {


		Estante emp = repository.findById(id).get();
		this.repository.deleteById(id);


		ModelAndView mv = new ModelAndView(URI + "form","estantes",emp);

		return mv;
	}

	@GetMapping(value = "render/{id}")
	public String renderLivros(@PathVariable("id") long id, Model model) {

		Optional<Estante> est = repository.findById(id);
		List<Livro> lista = est.
				get().
				getLivros();

		model.addAttribute("livros",lista);
		return URI + "renderLivros";
	}
}




