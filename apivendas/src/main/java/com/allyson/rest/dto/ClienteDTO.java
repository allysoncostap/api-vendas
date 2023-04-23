package com.allyson.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDTO {

    private String nome;
    private String cpf;
    private List<PedidoDTO> pedidos;
}
