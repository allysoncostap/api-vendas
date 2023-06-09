package com.allyson.rest.controller;


import com.allyson.domain.entity.ItemPedido;
import com.allyson.domain.entity.Pedido;
import com.allyson.domain.enums.StatusPedido;
import com.allyson.rest.dto.AtualizarStatusPedidoDTO;
import com.allyson.rest.dto.InformacaoItemPedidoDTO;
import com.allyson.rest.dto.InformacoesPedidoDTO;
import com.allyson.rest.dto.PedidoDTO;
import com.allyson.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;


@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

private PedidoService service;


    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

@GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return service.obterPedidoCompleto(id).map(p -> converter(p))
                .orElseThrow(()-> new ResponseStatusException(NOT_FOUND,"Pedido nao encontrado"));
}


@PatchMapping("{id}")
@ResponseStatus(NO_CONTENT)
public void updateStatus(@PathVariable Integer id, @RequestBody AtualizarStatusPedidoDTO atualizarStatusPedidoDTO){

        String novoStatus = atualizarStatusPedidoDTO.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
}



private InformacoesPedidoDTO converter(Pedido pedido){
 return  InformacoesPedidoDTO
        .builder()
        .codigo(pedido.getId())
        .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
        .cpf(pedido.getCliente().getCpf())
        .nomeCliente(pedido.getCliente().getNome())
        .total(pedido.getTotal())
         .status(pedido.getStatus().name())
        .items(converter(pedido.getItens()))
        .build();
}

private List<InformacaoItemPedidoDTO> converter(List<ItemPedido>itens){
if (CollectionUtils.isEmpty(itens)){
    return Collections.emptyList();
}
return itens.stream().map( item -> InformacaoItemPedidoDTO
        .builder().descricaoProduto(item.getProduto().getDescricao())
        .precoUnitario(item.getProduto().getPreco())
        .quantidade(item.getQuantidade())
        .build()).collect(Collectors.toList());

}




}
