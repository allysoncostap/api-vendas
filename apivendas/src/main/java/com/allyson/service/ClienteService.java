package com.allyson.service;

import com.allyson.domain.entity.Cliente;
import com.allyson.rest.dto.ClienteDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ClienteService {
    Cliente findById(Integer id);

    Cliente save(ClienteDTO clienteDTO);

    void delet(Integer id);

    void update(Integer id, ClienteDTO clienteDTO );

    List<Cliente> findAll(Cliente filtro);
}
