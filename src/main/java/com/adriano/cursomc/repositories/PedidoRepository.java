package com.adriano.cursomc.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.adriano.cursomc.domain.Pedido;




@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
