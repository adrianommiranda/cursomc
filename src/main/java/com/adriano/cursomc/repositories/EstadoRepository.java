package com.adriano.cursomc.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adriano.cursomc.domain.Estado;




@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
