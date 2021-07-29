package com.adriano.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriano.cursomc.domain.Pedido;
import com.adriano.cursomc.repositories.PedidoRepository;
import com.adriano.cursomc.services.exception.ObjectNotFoundException;

/*Agora vamos criar uma classe para representar a camada de serviços, capaz de atender o ControladorRest, 
 * relacionado a operações com categorias*/

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	//buscar caso o ID não exista eu lanço uma excecao
	public Pedido find(Integer id) { 
		 Optional<Pedido> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())); 
		}
	
	/*Então agora que vai receber esta excecao vai ser o a camada de recursos. Vai capturar a excecao e mandar um Json apropriado
	 * para a resposta http do meu recursos.
	 * para isto vamos criar Handler. Um obj especial que vai interceptar, caso haja uma excecao.
	 * O Handler que vai lançar a resposta http adequada no caso 404. Vai ser o manupulador e dexecoes do meus recursos*/
	
}
