package com.example.api.models;

import java.util.List;import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Locatario {

	@Id
	@Column(name = "CPF",nullable = false)
	@NotNull(message ="O CPF não pode ser nulo!")
	private String cpf; 
	
	@NotEmpty(message="O nome não pode ser vazio!")
	@Column(nullable = false)
	private String nome;
	
	@NotEmpty(message="A idade não pode ser vazia!")
	@NotNull(message="A idade não pode ser nula!")
	@Size(min = 0, max = 120,message="Idade inválida")
	@Column(nullable = false)
	private String idade;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "locatario")
	private List<Emprestimo> emprestimos;
	 
	
	public Locatario() {
		 
	}

	


	





	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}










	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}










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


	@Override
	public String toString() {
		return "Locatario [cpf=" + cpf + ", nome=" + nome + ", idade=" + idade + ", emprestimo=" +"]";
	}
	
	
}
