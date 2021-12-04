package com.example.api.DTOs;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.example.api.models.Estante;
import com.example.api.models.Livro;

public class EstanteDTO {


	private long ID;
	
	@NotEmpty(message="A seção não pode ser vazia!")
	private String secao;
	

	@NotEmpty(message="A localização não pode ser vazia!")
	private String localizacao; 
	

	private List<Livro> livros;
	
	
	
	public long getID() {
		return ID;
	}



	public void setID(long iD) {
		ID = iD;
	}



	public String getSecao() {
		return secao;
	}



	public void setSecao(String secao) {
		this.secao = secao;
	}



	public String getLocalizacao() {
		return localizacao;
	}



	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}



	public List<Livro> getLivros() {
		return livros;
	}



	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}



	public Estante toEstante() {
		
		Estante estanteREPO = new Estante();

		estanteREPO.setID(this.ID);
		estanteREPO.setLivros(this.livros);
		estanteREPO.setLocalizacao(this.localizacao);
		estanteREPO.setSecao(this.secao);
		
		return estanteREPO;
	}
	

	
}
