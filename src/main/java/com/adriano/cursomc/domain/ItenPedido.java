package com.adriano.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*Conforme aprendemos a classe de acssociacao não tem um id proprio, quem identifica ela sao so dois obkjetos associados a ela
 *o produto e o pedido. Umas das formas deimplementa e criar uma chave composta contendo o produto e o pedido(que iremos fazer).
 *  iremos criar uma classe auxiliar no domain ItemPedidoPK que vai ser a minha chave composta*/
@Entity
public class ItenPedido implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/*essa classe vai ter como id um obj do tipo ItemPedidoPK - quando voce faz uma entidade do JPA, tendo como atributo 
	 *uma outra classe  temos que ir na outra classe(ItemPedidoPK) e colocar @Embeddable - para dizer que ela vai ser 
	 *um subtipo*/
	@JsonIgnore//não vai ser serializado, o pedido não serializa ninguém(pedido e produto)
	@EmbeddedId //é um id imbutido em um tipo auxiliar
	private ItemPedidoPK id = new ItemPedidoPK();
	
	private double desconto;
	private int quantidade;
	private double preco;
	
	
	public ItenPedido() {
	
	}


	public ItenPedido(Pedido pedido, Produto produto, double desconto, int quantidade, double preco) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}


	public ItemPedidoPK getId() {
		return id;
	}


	public void setId(ItemPedidoPK id) {
		this.id = id;
	}


	public double getDesconto() {
		return desconto;
	}


	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public double getPreco() {
		return preco;
	}


	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	//Fizemos manual get pedidos e produtos. Para ter acesso direto ao peido e ao produto fora da minha classe ItemPedido
	@JsonIgnore//sempre que começa com get vai serializar por isto temos que ignorar
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	
	public Produto getProduto() {
		return id.getProduto();
	}


	
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
		ItenPedido other = (ItenPedido) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
