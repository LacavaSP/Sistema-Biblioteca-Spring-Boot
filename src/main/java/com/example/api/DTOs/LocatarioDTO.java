package com.example.api.DTOs;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.api.models.Locatario;

public class LocatarioDTO {


	@NotNull(message ="O CPF não pode ser nulo!")
	private String cpf; 
	
	@NotEmpty(message="O nome não pode ser vazio!")
	private String nome;
	
	@NotEmpty(message="A idade não pode ser vazia!")
	@NotNull(message="A idade não pode ser nula!")
	@Size(min = 0, max = 120,message="Idade inválida")
	private String idade;
	





	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}



	public Locatario toLocatario() {
		
		Locatario loc = new Locatario();
		
		loc.setNome(this.nome);
		loc.setCpf(this.cpf);
		loc.setIdade(this.idade);
		
		return loc;
			}
	
	@Override
	public String toString() {
		return "Locatario [cpf=" + cpf + ", nome=" + nome + ", idade=" + idade + ", emprestimo=" + "]";
	}
	
	
}
