package com.allyson.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemPedidoDTO {


    private  Integer produto;
    private Integer quantidade;


}
