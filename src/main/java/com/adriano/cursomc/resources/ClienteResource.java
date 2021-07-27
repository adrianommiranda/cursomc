/*Pacote de recusos em inglês
 * é um padrão aonde vamos gravar as nossas classes que são controladoras REST
 * São chamadas de Resourses (recursos) 
 * Como nosso desenvolvimento ele é orientado a domínio, classes de domínio, sempre que for criar um recurso, sempre vamos usar
 * como padrão o nome da classe de dominio mais o sufixo Resource   - ClienteResource*/

package com.adriano.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adriano.cursomc.domain.Cliente;
import com.adriano.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")/*Criando um um and point REST.*/ 
/*Feito isto, criamos uma classe, que vai ser um controlador REST e vai responder por este and point /Clientes*/
public class ClienteResource {

	
	@Autowired
	private ClienteService service;
	
	//Sofisticando o metodo
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Cliente obj = service.find(id);//buscar
		
		return ResponseEntity.ok().body(obj);
	
		
		
	}
	
	
	
}
