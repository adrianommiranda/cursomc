package com.adriano.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.adriano.cursomc.domain.ItenPedido;



@Repository
public interface ItemPedidoRepository extends JpaRepository<ItenPedido, Integer>{

}