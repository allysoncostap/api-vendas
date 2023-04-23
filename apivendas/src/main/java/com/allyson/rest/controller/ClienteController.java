package com.allyson.rest.controller;

import com.allyson.domain.entity.Cliente;
import com.allyson.domain.repository.ClientesRepository;
import com.allyson.rest.dto.ClienteDTO;
import com.allyson.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;



    @GetMapping("/{id}")
    public Cliente getClienteById( @PathVariable Integer id ){
        return clienteService
                .findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save( @RequestBody ClienteDTO clienteDTO ){
        return clienteService.save( clienteDTO);

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ){
        clienteService.delet(id);


    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id,
                        @RequestBody ClienteDTO clienteDTO ){
        clienteService.update(id , clienteDTO);

    }

    @GetMapping
    public List<Cliente> find( Cliente filtro ){
return clienteService.findAll(filtro);
    }

}
