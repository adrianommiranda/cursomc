/*Pacote de recusos em inglês
 * é um padrão aonde vamos gravar as nossas classes que são controladoras REST
 * São chamadas de Resourses (recursos) 
 * Como nosso desenvolvimento ele é orientado a domínio, classes de domínio, sempre que for criar um recurso, sempre vamos usar
 * como padrão o nome da classe de dominio mais o sufixo Resource   - PedidoResource*/

package com.adriano.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adriano.cursomc.domain.Pedido;
import com.adriano.cursomc.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")/*Criando um um and point REST.*/ 
/*Feito isto, criamos uma classe, que vai ser um controlador REST e vai responder por este and point /categorias*/
public class PedidoResource {

	
		
	@Autowired
	private PedidoService service;
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		/*Esse metodo vai receber um id que vai vir na minha url
		 * Para que o spring que o id da url, vate ter que ir pada o id da variavel uasamos o (@PathVariable Integer id
		 * Esse tipo ResponseEntity - é um tipo especial do spring que já encapsula, armazena várias informações de uma 
		 * resposta http para um serviço Rest
		 * O tipo dele vai ser uma ?, pode ser qualquer tipo, pode encontrar ou não encontrar       */
		
		
		Pedido obj = service.find(id);//buscar
		
		/*Vou mandar meu método retornar o objeto do tipo ResponseEntity - metodo ok dizendo que a operação ocorreu com sucesso
		 e essa resposta vai ter como corpo o objeto obj que eu busquei que é acategoria.*/
		return ResponseEntity.ok().body(obj);
	
		
		
	}
	
	
	
}
