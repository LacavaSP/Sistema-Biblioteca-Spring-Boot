package com.example.api.models;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Livro {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	
	@NotEmpty(message="O nome não pode ser vazio!")
	@Column(nullable = false)
	private String nome;
	
	@NotEmpty(message="O gênero não pode ser vazio!")
	@Column(name = "gênero", nullable = false)
	private String genero;
	
	@NotNull(message="O número de páginas não pode ser nulo!")
	@Min(30)
	@Column(name= "número_de_páginas", nullable = false)
	private int numeroDePaginas;
	
	@NotEmpty(message="O nome do autor não pode ser vazio!")
	@Column(nullable = false)
	private String autor;
	
	@NotNull(message="O número da edição não pode ser nulo!")
	@Column(name = "edição", nullable = false)
	private int edicao;
	
	@NotNull(message="O ID da estante não pode ser nulo!")
	@ManyToOne
	private Estante estante;
	
	@OneToOne (cascade = CascadeType.ALL, mappedBy = "livro")
	private Emprestimo emprestimo;

	@Column(nullable = false)
	private String disponibilidade;
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public void setNumeroDePaginas(int numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

	public Estante getEstante() {
		return estante;
	}

	public void setEstante(Estante estante) {
		this.estante = estante;
	}

	
	public String getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(String disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	
	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	@Override
	public String toString() {
		return "Livro [ID=" + ID + ", nome=" + nome + ", genero=" + genero + ", numeroDePaginas=" + numeroDePaginas
				+ ", autor=" + autor + ", edicao=" + edicao;
	}
	
	
	
}
