package com.example.api.DTOs;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.api.models.Emprestimo;
import com.example.api.models.Estante;
import com.example.api.models.Livro;

public class LivroDTO {

	private long ID;
	
	@NotEmpty(message="O nome não pode ser vazio!")
	private String nome;
	
	@NotEmpty(message="O gênero não pode ser vazio!")
	private String genero;
	
	@NotNull(message="O número de páginas não pode ser nulo!")
	@Min(30)
	private int numeroDePaginas;
	
	@NotEmpty(message="O nome do autor não pode ser vazio!")
	private String autor;
	
	@NotNull(message="O número da edição não pode ser nulo!")
	private int edicao;
	
	@NotNull(message="O ID da estante não pode ser nulo!")
	private Estante estante;

	private String disponibilidade;
	
	private Emprestimo emprestimo;
	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public LivroDTO() {
		
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	
	public String getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(String disponibilidade) {
		this.disponibilidade = disponibilidade;
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

	public Livro toLivro() {
		
		Livro livroREPO = new Livro();
		livroREPO.setID(this.ID);
		livroREPO.setAutor(this.autor);
		livroREPO.setEdicao(this.edicao);
		livroREPO.setEstante(this.estante);
		livroREPO.setGenero(this.genero);
		livroREPO.setNome(this.nome);
		livroREPO.setNumeroDePaginas(this.numeroDePaginas);
		livroREPO.setEmprestimo(emprestimo);
		return livroREPO;
	}
	
	@Override
	public String toString() {
		return "LivroDTO [ID=" + ID + ", nome=" + nome + ", genero=" + genero + ", numeroDePaginas=" + numeroDePaginas
				+ ", autor=" + autor + ", edicao=" + edicao + ", estante=" + estante + "]";
	}
	
	
	
}
