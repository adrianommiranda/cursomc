package com.adriano.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriano.cursomc.domain.Categoria;
import com.adriano.cursomc.repositories.CategoriaRepository;

/*Agora vamos criar uma classe para representar a camada de serviços, capaz de atender o ControladorRest, 
 * relacionado a operações com categorias*/

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	//buscar
	public Categoria find(Integer id) { 
		 Optional<Categoria> obj = repo.findById(id); 
		return obj.orElse(null); 
		} 

	/*Depois disto. Temos que atualizar o ControladorRest para ele ser capaz de buscar a categoria, na CategoriaResouce*/
	
}
