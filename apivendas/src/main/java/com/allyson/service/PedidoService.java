package com.allyson.service;


import com.allyson.domain.entity.Pedido;
import com.allyson.rest.dto.PedidoDTO;


import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id );
}
