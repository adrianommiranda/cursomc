package com.adriano.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.adriano.cursomc.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;
	private String cpfOuCnpj;
	//private TipoCliente tipo; -- foi atualizado
	private Integer tipo;
	
	//criando a associação - endereco tem um clientes e o cliente tem varios endereço r varios telefones
	/*@JsonManagedReference Associacao siclica, o cliente conhece os enderecos e os enderecos conhece o cliente. 
	 * Como quero fazer um and point que eu passe o cliente e venha o cliente com os endereços dele, eu vou fazer a protecao
	 * da seginte forma. O cliente pode serializar os enderecos dele, porem o endereco não.
	 * Na classe endereco coloco @JsonBackReference*/
	@JsonManagedReference 
	@OneToMany(mappedBy = "cliente")
	private List<Endereco> endereco = new ArrayList<>();
	
	
	/*Tinhamos modelado o telefonne como uma entidade fraca. Nã tem Id propio e é dependente totalmente do cliente
	 * Neste caso como é apenas um string, não vou criar a classe 
	 * Vou implementar como sendo uma colecao de strings associadas ao cliente
	 * Vou usar o tipo Set. Set é um conjunto, não aceita repeticao.
	 * Estou criando um conjunto de strings que não vai ter repeticao. 
	 * Eu crio ela e já inicio com o  HashSet<>()
	 * Com isto representamos o telefone com o conjunto de strings*/
	@ElementCollection
	@CollectionTable(name="Telefone")
	private Set<String> telefones = new HashSet<>();
	
	
	//Associando cliente ao pedido. Pedido tem varios pedidos. Mas ela é bidirecional. O pedidos conhece os clientes.
	@JsonBackReference//pedidos do cliente não vao ser serializados
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();
	
	
	//construtor
	public Cliente() {
		
	}

	
	
	

	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		//this.tipo = tipo;  -- foi atualizado
		this.tipo = tipo.getCod();
	}





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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}


	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}


	public TipoCliente getTipo() {
		//return tipo;  -- foi atualizado
		return TipoCliente.toEnum(tipo);
	}


	public void setTipo(TipoCliente tipo) {
		//this.tipo = tipo;  -- foi atualizado
		this.tipo = tipo.getCod();
	}


	public List<Endereco> getEndereco() {
		return endereco;
	}


	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}


	public Set<String> getTelefones() {
		return telefones;
	}


	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}


	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}





	
	
	
	
	
	
	
}
