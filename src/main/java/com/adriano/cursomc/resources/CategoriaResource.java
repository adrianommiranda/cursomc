/*Pacote de recusos em inglês
 * é um padrão aonde vamos gravar as nossas classes que são controladoras REST
 * São chamadas de Resourses (recursos) 
 * Como nosso desenvolvimento ele é orientado a domínio, classes de domínio, sempre que for criar um recurso, sempre vamos usar
 * como padrão o nome da classe de dominio mais o sufixo Resource   - CategoriaResource*/

package com.adriano.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")/*Criando um um and point REST.*/ 
/*Feito isto, criamos uma classe, que vai ser um controlador REST e vai responder por este and point /categorias*/
public class CategoriaResource {

	
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		Categoria c1 = new Categoria(1,"Casa");
		Categoria c2 = new Categoria(2,"Mesa");
		
		List<Categoria> lista = new ArrayList<>();
		lista.add(c1);
		lista.add(c2);
		return lista;
	}
	
	
	
}
