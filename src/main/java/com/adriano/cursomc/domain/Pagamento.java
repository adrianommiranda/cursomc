package com.adriano.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.adriano.cursomc.domain.enums.EstadoPagamento;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) //isso é feito na superclasse
/*A linha acima - criando tabelas join - nao criei um tabelao. Gerei uma tab para cada sub classe
 *Geralmente quando se tem muitos atributos na subclasse, se coloca tabelas independentes, quando se tem
 *poucos atributos fazemos o tabelao */
public abstract class Pagamento implements Serializable {
	
private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private Integer estado;
	
	//na classe pagamento vai ter um pedido
	/*Realizando o mapeamento jpa(objeto relacional) - Pagamento e pedido é 1x1. Quando é 1x1, nos queremos que o pagamento,
	 * tenha o mesmo id do pedido daquele pagamento. Vamos começar mapeando o id do pagamento, para informar que sera o mesmo 
	 * id do pedido 
	 * coluna correspondente ao ID do pedido @JoinColumn(name="pedido_id")
	 * Para garantir que este id do pagamento seja o mesmo do pedido @MapsId*/
	
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId
	private Pedido pedido;
	
	
	public Pagamento() {
		
		
	}
	
	
	


	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = estado.getCod();
		this.pedido = pedido;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}


	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}


	public Pedido getPedido() {
		return pedido;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
		Pagamento other = (Pagamento) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
