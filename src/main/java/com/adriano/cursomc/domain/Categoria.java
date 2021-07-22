package com.adriano.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity /*Anotações do pacote jpa- para fazer o mapeamento objeto-relacional */
public class Categoria implements Serializable {
	/*Serializable é uma interface que diz que esta classe, os objetos dela poderá ser convertido para uma sequencia de bits, para que
	 * os objetos sejam gravados em arquivos, trafegar em redes. Exigencia da linguagem Java */
	
	private static final long serialVersionUID = 1L; /*Quando uma classe implementa umSerializable, ele precisa de um número de 
	versao padrao, neste caso a minha classe é a versão 1 dela, primeira versao */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Estrategia de geração automatica dos ID da categoria
	private Integer id;
	private String nome;
	
	
	
	//***********************Consturores
	public Categoria() {
		
	}

	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	
	
	//***********************GETS e SETS
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	//***********************TO STRING
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + "]";
	}

	
	//***********************HASH CODE e EQUALS --- Compara os objetos por valor
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
}
