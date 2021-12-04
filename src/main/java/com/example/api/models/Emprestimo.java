package com.example.api.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Emprestimo {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	 
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(nullable = false, name = "Data_do_emprestimo")
	private Date data;
		
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(nullable = false, name = "Data_do_limite_emprestimo")
	private Date datalimite;
	
	@ManyToOne 
	@NotNull(message="O CPF não pode ser nulo!")
	private Locatario locatario;
	
	@Column(name = "LivroID")
	private long livroID;
	
	@Column(name = "CPFLC")
	private String cpf;

	@OneToOne 
	private Livro livro;
	
	
	public long getLivroID() {
		return livroID;
	}

	public void setLivroID(long livroID) {
		this.livroID = livroID;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDatalimite() {
		return datalimite;
	}

	public void setDatalimite(Date datalimite) {
		this.datalimite = datalimite;
	}

	public Locatario getLocatario() {
		return locatario;
	}

	public void setLocatario(Locatario locatario) {
		this.locatario = locatario;
	}
	
	public String toCPF() {
		this.cpf = locatario.getCpf();
		return cpf;
	}

	
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	@Override
	public String toString() {
		return "Emprestimo [ID=" + ID + ", data=" + data + ", datalimite=" + datalimite + ", locatario=" + locatario
				+ "]";
	}
	
	
}
