package com.adriano.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Produto  implements Serializable {
		
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco;
	
	/*Falta fazer o mapeamento entre classes, os relacionamentos. Entre a classe Produto e Categoria M X M.
	 Quando se tem uma tabela M X M temque se criar uma terceira tabela no meio, contendo os dois ids das tabelas. 
	 No JPA quandose tem listas dos dois(uma) lados tem que se usar a notacao @ManyToMany em um dos dois lados e a 
	 @JoinTable(nesta notação em vou definir quem vai ser a tabelinha que vai fazer p M x M lá no banco de dados relaconal)*/
	
	
	//Declarar uma lista de categorias.Um produto tem varias categorias
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA", 
		joinColumns = @JoinColumn(name = "produto_id"),
		inverseJoinColumns = @JoinColumn(name = "categoria_id")
			)//nome da tabela do meio e as colunas id
	private List<Categoria> categorias = new ArrayList<>();
	
	
	/*************************Construtores                    */
	public Produto() {
		
	}


	public Produto(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}


		
	/*************************Final Construtores                    */
	
	
	/*************************GET e SET                    */
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


	public Double getPreco() {
		return preco;
	}


	public void setPreco(Double preco) {
		this.preco = preco;
	}


	public List<Categoria> getCategorias() {
		return categorias;
	}


	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}


	
	

	
	/*************************Final GET e SET                   */
	
	
	/*************************hashCode                   */
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	
	/*************************Final hashCode                   */
}