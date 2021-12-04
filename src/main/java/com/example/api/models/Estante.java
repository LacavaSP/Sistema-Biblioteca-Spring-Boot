package com.example.api.models;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
public class Estante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long ID;
	
	

	@NotEmpty(message="A seção não pode ser vazia!")
	@Column (name = "seção")
	private String secao;
	

	@NotEmpty(message="A localização não pode ser vazia!")
	@Column (name = "localização")
	private String localizacao; 
	
	
 
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estante")
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

	@Override
	public String toString() {
		return "Estante [ID=" + ID + ", secao=" + secao + ", localizacao=" + localizacao + ", livros=" + livros + "]";
	}
	
	
	
}
