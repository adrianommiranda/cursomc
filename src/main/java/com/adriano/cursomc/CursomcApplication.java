package com.adriano.cursomc;

import java.text.SimpleDateFormat;
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
import com.adriano.cursomc.domain.ItemPedidoPK;
import com.adriano.cursomc.domain.ItenPedido;
import com.adriano.cursomc.domain.Pagamento;
import com.adriano.cursomc.domain.PagamentoComBoleto;
import com.adriano.cursomc.domain.PagamentocomCartao;
import com.adriano.cursomc.domain.Pedido;
import com.adriano.cursomc.domain.Produto;
import com.adriano.cursomc.domain.enums.EstadoPagamento;
import com.adriano.cursomc.domain.enums.TipoCliente;
import com.adriano.cursomc.repositories.CategoriaRepository;
import com.adriano.cursomc.repositories.CidadeRepository;
import com.adriano.cursomc.repositories.ClienteRepository;
import com.adriano.cursomc.repositories.EnderecoRepository;
import com.adriano.cursomc.repositories.EstadoRepository;
import com.adriano.cursomc.repositories.ItemPedidoRepository;
import com.adriano.cursomc.repositories.PagamentoRepository;
import com.adriano.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired 
	PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
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
		
		//Instanciando Pedido - vamos criar um obj auxiliar SimpleDateFormat
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");//mascara de formatação para instanciar uma data
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		//instanciando pagamentos
		Pagamento pagto1 = new PagamentocomCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		//Salvando no banco
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		
		//Instanciando Item_pedido
		ItenPedido ip1 = new ItenPedido(ped1, p1, 0.00, 1, 2000.00);
		ItenPedido ip2 = new ItenPedido(ped1, p3, 0.00, 2, 80.00);
		ItenPedido ip3 = new ItenPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip2));
		p3.getItens().addAll(Arrays.asList(ip3));
		
		
		//salvando no banco
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
