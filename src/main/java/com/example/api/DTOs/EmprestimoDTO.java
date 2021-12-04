package com.example.api.DTOs;

import java.util.Date;


import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.api.models.Emprestimo;
import com.example.api.models.Livro;
import com.example.api.models.Locatario;


public class EmprestimoDTO {


	private long ID;
	

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date data;
	
	private String cpf;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date datalimite;
	
	@NotNull(message="O CPF não pode ser nulo!")
	private Locatario locatario;

	private Livro livro;
	
	private long livroID;
	
	
	
	public long getLivroID() {
		return livroID;
	}

	public void setLivroID(long livroID) {
		this.livroID = livroID;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
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

	public Emprestimo toEmprestimo() {
		
		Emprestimo emp = new Emprestimo();
		emp.setData(this.data);
		emp.setDatalimite(this.datalimite);
		emp.setLocatario(this.locatario);
		emp.setCpf(toCPF());
		emp.setLivro(this.livro);
		emp.setLivroID(this.livro.getID());
		return emp;
	}

	@Override
	public String toString() {
		return "Emprestimo [ID=" + ID + ", data=" + data + ", datalimite=" + datalimite + ", locatario=" + locatario
				+ "]";
	}
	
	
}
