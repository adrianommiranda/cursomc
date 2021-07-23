package com.adriano.cursomc.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adriano.cursomc.domain.Categoria;


/*Esse CategoriaRepository não vai ser uma classe, vai ser uma interface. Esta interface vai estender ou seja vai herdar 
 * de uma outra interface chamada JpaRepository - tipo especial do spring capaz de acessar os dados com base em um tipo que você
 * passar, objetos do tipo categoria e em seguida o tipo do atributo identificador desse objeto - integer
 * O objeto deste tipo(CategoriaRepository), vai ser capaz de realizar oprrações de acesso a dados(buscas, salvar deletar, sql)
 * referente ao objeto categoria, que porsua vez está mapeado com a tabela categoria lá no meu banco de dados
 * 
 * Com isto criamos o CategoriaRepository, que é o objeto da camada de acesso a dados referente ao tipo categoria. 
 * 
 * Agora vamos criar uma classe para representar a camada de serviços, capaz de atender o ControladorRest, relacionado a operações
 *   com categorias*/

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
