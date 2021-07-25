package com.adriano.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.adriano.cursomc.domain.Categoria;
import com.adriano.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
/*CommandLineRunner - Permitir implementar um método auxiliar para executar alguma ação quando  a
  aplicação iniciar*/
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	//Assinatura do metodo CommandLineRunner que eu vou colocar a instanciação dos objetos
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		//Salvando no banco
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
	}

}
