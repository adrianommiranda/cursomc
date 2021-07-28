package com.adriano.cursomc.domain;

import javax.persistence.Entity;

import com.adriano.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentocomCartao extends Pagamento{
	private static final long serialVersionUID = 1L; //é obrigatoriocolocar nas subclasses

	private Integer numeroDeparcelas;
	
	public PagamentocomCartao() {
		
	}

	public PagamentocomCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeparcelas) {
		super(id, estado, pedido);
		
		this.numeroDeparcelas = numeroDeparcelas;
	}

	public Integer getNumeroDeparcelas() {
		return numeroDeparcelas;
	}

	public void setNumeroDeparcelas(Integer numeroDeparcelas) {
		this.numeroDeparcelas = numeroDeparcelas;
	}
	
	
}
