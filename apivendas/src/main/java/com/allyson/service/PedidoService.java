package com.allyson.service;


import com.allyson.domain.entity.Pedido;
import com.allyson.domain.enums.StatusPedido;
import com.allyson.rest.dto.PedidoDTO;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id );

    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
