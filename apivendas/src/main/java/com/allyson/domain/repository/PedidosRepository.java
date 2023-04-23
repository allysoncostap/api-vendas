package com.allyson.domain.repository;


import com.allyson.domain.entity.Cliente;
import com.allyson.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface PedidosRepository extends JpaRepository <com.allyson.domain.entity.Pedido, Integer> {
List<Pedido> findAllByCliente(Cliente cliente);

@Query("select p from Pedido  p left join fetch p.itens where p.id = :id")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
