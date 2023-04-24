package com.allyson.rest.dto;


import com.allyson.domain.entity.Pedido;
import com.sun.xml.bind.v2.TODO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDTO {

    private String nome;
    private String cpf;
    private Set<PedidoDTO> pedidos; //TODO
}
