package com.adriano.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;





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
	
	
	//criando uma lista e produtos
	//Declarei a associacao da categoria com os produtos que é uma lista de produtos e iniciei ela
	/*@ManyToMany(mappedBy = "Categoria") Relacionamento entre as tabelas*/
	
	/*@JsonManagedReference Referencia gerenciada pelo Json, faz isto no lado que vc queira que venha os objetos associados
	 * No  outro lado na lista de categorias @JsonBackReference. Este faz: do outro lado da associacao já foram buscados os obj.
	 * Entao agora em não busco  mais.
	 * Ele vai omitir então a lista de categorias para cada produto
	 * */
	
	@ManyToMany(mappedBy = "categorias")
	private List<Produto> produtos = new ArrayList<>();
	
	
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


	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
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
