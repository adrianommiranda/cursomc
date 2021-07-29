package com.adriano.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ItemPedidoPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*Vai ter uma referencia para pedido e uma referencia para produto, que sao os dois conceitos dos quais ela faz 
	 * o meio de campo Entao ela prewcisa de uma referencia para os dois. os dois juntos sao quem identifica o item
	 * pedido.*/
	@ManyToOne //mapeando o itempedido tem que conhecer o pedido e um produto
	@JoinColumn(name="pedido_id")//no caso do atributo pedido
	private Pedido pedido;
	
	@ManyToOne //mapeando o itempedido tem que conhecer o pedido e um produto
	@JoinColumn(name="produto_id")//no caso do atributo produto
	private Produto produto;
	
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(pedido, produto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedidoPK other = (ItemPedidoPK) obj;
		return Objects.equals(pedido, other.pedido) && Objects.equals(produto, other.produto);
	}
	
	
	
	
}
