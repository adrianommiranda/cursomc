package com.adriano.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.adriano.cursomc.domain.Categoria;
import com.adriano.cursomc.domain.Cidade;
import com.adriano.cursomc.domain.Cliente;
import com.adriano.cursomc.domain.Endereco;
import com.adriano.cursomc.domain.Estado;
import com.adriano.cursomc.domain.Produto;
import com.adriano.cursomc.domain.enums.TipoCliente;
import com.adriano.cursomc.repositories.CategoriaRepository;
import com.adriano.cursomc.repositories.CidadeRepository;
import com.adriano.cursomc.repositories.ClienteRepository;
import com.adriano.cursomc.repositories.EnderecoRepository;
import com.adriano.cursomc.repositories.EstadoRepository;
import com.adriano.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
/*CommandLineRunner - Permitir implementar um método auxiliar para executar alguma ação quando  a
  aplicação iniciar*/
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	//Assinatura do metodo CommandLineRunner que eu vou colocar a instanciação dos objetos
	@Override
	public void run(String... args) throws Exception {
		
		//Instanciando categorias
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		//Instanciando produtos
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		
		//Associando as categorias e seus produtos(categorias conhecem seusprodutos)
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		//Produtos ainda não conhecem as categorias deles  - associacao entre produtos e categorias
		//Temos que fazeras mesma coisa
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		//Salvando no banco
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		//Instanciando estados
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");		

		//Instanciando cidade e cada cidade já conhece seu estado
		Cidade c1 = new Cidade(null,"Uberlandia", est1);
		Cidade c2 = new Cidade(null,"São Paulo", est2);
		Cidade c3 = new Cidade(null,"Campinas", est2);
			
		//cada estado conhecendo sua cidade
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		//salvando no banco
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		//Instanciando Clientes
		Cliente cli1 = new Cliente(null, "Maria Silva","Maria@gmail.com", "03717253484", TipoCliente.PESSOAFISICA);
		
		//Instanciando Telefones
		cli1.getTelefones().addAll(Arrays.asList("27363323","9383393"));
		
		//Instanciando Eendereços
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apt 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		Endereco e3 = new Endereco(null, "Rua Flores", "300", "Apt 303", "Jardim", "38220834", cli1, c1);
		
		
		//O cliente temque conhecer os enderecos deles
		cli1.getEndereco().addAll(Arrays.asList(e1,e2));
		
		//Vamos salvar primeiro o Cliente.class Ele é idependente do enredeco
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
	}

}
