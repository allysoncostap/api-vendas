package com.allyson.service.impl;

import com.allyson.domain.entity.Cliente;
import com.allyson.domain.entity.ItemPedido;
import com.allyson.domain.entity.Pedido;
import com.allyson.domain.entity.Produto;
import com.allyson.domain.repository.ClientesRepository;
import com.allyson.domain.repository.ProdutosRepository;
import com.allyson.rest.dto.ClienteDTO;
import com.allyson.rest.dto.ItemPedidoDTO;
import com.allyson.rest.dto.PedidoDTO;
import com.allyson.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Service
public class ClienteServiceImp  implements ClienteService {

    @Autowired
    private ClientesRepository repository;



    @Override
    public Cliente findById(@PathVariable Integer id) {
        Optional<Cliente> clienteOptional = repository.findById(id);
        Cliente cliente = clienteOptional.orElseThrow(() ->
                new ResponseStatusException(NOT_FOUND,
                        "Cliente não encontrado"));
        return cliente;
    }

    @Override
    public Cliente save(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setNome(clienteDTO.getNome());
        return repository.save(cliente);

    }

    @Override
    public void delet(Integer id) {
        repository.findById(id)
                .map(cliente -> {
                    repository.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));
    }

    @Override
    public void update(Integer id, ClienteDTO clienteDTO) {

    }

    /**
     * Estava tentando convernter as set e a lista ,
     *
     *
     *

    @Override
    public void update(Integer id, ClienteDTO clienteDTO) {Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));


        cliente.setId(id);
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
       List<Pedido> pedidos= clienteDTO.getPedidos();


    }
*/



    @Override
    public List<Cliente> findAll(Cliente filtro) {
        return null;
    }




}
