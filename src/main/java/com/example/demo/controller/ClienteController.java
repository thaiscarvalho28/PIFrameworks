package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.services.ClienteService;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity cadastrarCliente(@RequestBody Cliente cli) {

        clienteService.cadastrarCliente(cli);

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}")
    ResponseEntity removerCliente(@PathVariable Long id) {

       clienteService.excluirCliente(id);
       
       return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    void editarCliente() {
        System.out.println("edita");
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Cliente> mostraCliente(@PathVariable Long id) {
        
        Cliente cli;
        try {
            cli = clienteService.buscaCliente(id);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(cli, HttpStatus.OK);
    }

}
