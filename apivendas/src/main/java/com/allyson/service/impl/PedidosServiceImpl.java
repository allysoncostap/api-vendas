package com.allyson.service.impl;


import com.allyson.domain.entity.Cliente;
import com.allyson.domain.entity.ItemPedido;
import com.allyson.domain.entity.Pedido;
import com.allyson.domain.entity.Produto;
import com.allyson.domain.enums.StatusPedido;
import com.allyson.domain.repository.ClientesRepository;
import com.allyson.domain.repository.ItemsPedidosRepository;
import com.allyson.domain.repository.PedidosRepository;
import com.allyson.domain.repository.ProdutosRepository;
import com.allyson.exception.PedidoNaoEncontradoException;
import com.allyson.rest.dto.ItemPedidoDTO;
import com.allyson.rest.dto.PedidoDTO;
import com.allyson.service.PedidoService;
import exception.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PedidosServiceImpl implements PedidoService {

    private final PedidosRepository pedidosRepository;
    private final ProdutosRepository produtosRepository;
    private final ClientesRepository clientesRepository;
    private final ItemsPedidosRepository itemsPedidosRepository;

    @Transactional
    public Pedido salvar(PedidoDTO dto) {

        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository.findById(idCliente).orElseThrow(() ->
                new RegraNegocioException("Codigo do cliente invalido"));


        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemPedido = converterItem(pedido, dto.getItems());
        pedidosRepository.save(pedido);
        itemsPedidosRepository.saveAll(itemPedido);

        pedido.setItens(itemPedido);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidosRepository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        pedidosRepository.findById(id).map( pedido ->{
                pedido.setStatus(statusPedido);
        return pedidosRepository.save(pedido);

    }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }


    private List<ItemPedido> converterItem(Pedido pedido, List<ItemPedidoDTO> items) {

        if (items.isEmpty()) {
            throw new RegraNegocioException("Não é possivel realizar um pedido sem items.");
        }

        return items
                .stream().map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("codigo de produto invalido" + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    }
}
