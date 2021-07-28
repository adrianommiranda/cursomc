package com.adriano.cursomc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Pedido implements Serializable {
	
private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Date instante; //instante que foi feito o pedido
	
	//criando as associacoes com pagamento. Pedido tem um pagamento
	/*Isto é necessario, se não da um erro de entidade transiente quando vai salvar um pedido e o pagamento dele.
	É uma obrigatoriedade do jpa cascade = CascadeType.ALL, mappedBy = "pedido" 
	Com isto fazemos omapeamento bi-direcional 1x1 e ainda garantimos que o id do pagamento vai ser o mesmo pedido
	correspondente a ele.  */
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;
	
	//Associando pedido ao cliente. Pedido tem um cliente. Mas ela é bidirecional. O cliente conhece os pedidos.
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	//Associando pedido ao endereco. Pedido tem um endereco e ela é direcionada. O endereco não tem que conhecer o pedido
	@ManyToOne
	@JoinColumn(name = "endereco_entrega_id")
	private Endereco enderecoDeEntrega;
	
	
	/*Realizando o mapeamento jpa(objeto relacional) - Pagamento e pedido é 1x1. Quando é 1x1, nos queremos que o pagamento,
	 * tenha o mesmo id do pedido daquele pagamento. Vamos começar mapeando o id do pagamento, para informar que sera o mesmo 
	 * id do pedido*/
	
	public Pedido() {
		
	}


	public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getInstante() {
		return instante;
	}


	public void setInstante(Date instante) {
		this.instante = instante;
	}


	public Pagamento getPagamento() {
		return pagamento;
	}


	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}


	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
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
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
