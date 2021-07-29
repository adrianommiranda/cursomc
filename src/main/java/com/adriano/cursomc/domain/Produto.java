package com.adriano.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA", 
		joinColumns = @JoinColumn(name = "produto_id"),
		inverseJoinColumns = @JoinColumn(name = "categoria_id")
			)//nome da tabela do meio e as colunas id
	private List<Categoria> categorias = new ArrayList<>();
	
	
	//Vai ter que conhecer os itens pedidos associados a ela
	@JsonIgnore
	@OneToMany(mappedBy = "id.produto")
	private Set<ItenPedido> itens = new HashSet<>();
	
	
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
	
	
	/*Podemos aproveitar implementar este acesso que esta previsto no diagrama. Um produto conhece os pedidos dele.
	 * Entao posso criar um getpedidos, varrendo os itens pedidos e montando uma lista de pedidos associados a estes itens.
	 */
	@JsonIgnore //se não ignorar vai serializar os pedidos associados aos produto. (referencia ciclica)
	public List<Pedido> getPedidos(){
		List<Pedido> lista = new ArrayList<>();
		for(ItenPedido x : itens) {
			lista.add(x.getPedido());
		}
		return lista;
	}
	
	
	
	
	
	
	
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


	public Set<ItenPedido> getItens() {
		return itens;
	}


	public void setItens(Set<ItenPedido> itens) {
		this.itens = itens;
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


