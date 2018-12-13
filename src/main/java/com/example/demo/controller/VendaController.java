/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Carrinho;
import com.example.demo.model.ItemCarrinho;
import com.example.demo.model.ItemVenda;
import com.example.demo.model.Produto;
import com.example.demo.model.Venda;
import com.example.demo.services.CarrinhoService;
import com.example.demo.services.ItemCarrinhoService;
import com.example.demo.services.VendaService;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jaqueline
 */
@RestController
@RequestMapping(value="/venda")
public class VendaController {
    @Autowired
    CarrinhoController carrinhoController;
    
    @Autowired
    VendaService vendaService;
    
    @Autowired
    CarrinhoService carrinhoService;
    
    @Autowired
    ItemCarrinhoService itemCarrinhoService;
    
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity cadastrarVenda(@RequestBody Produto prod, @RequestHeader HttpHeaders headers) {
        Carrinho car = carrinhoController.retornaTodoCarrinho(headers);
        
        Venda v = new Venda();
        ArrayList<Produto> produtos = new ArrayList();
        
        for (ItemCarrinho i : car.getItensCarrinho()) {
            ItemVenda iv = new ItemVenda();
            iv.setProduto(i.getProduto());
            iv.setCusto(i.getProduto().getCusto());
            iv.setQuantidade(i.getQuantidade());
            iv.setVenda(v);

            produtos.add(prod);
           
        }
        
        v.setProdutos(produtos);

        v.setData(new Date(System.currentTimeMillis()));
        
        v.setClient(car.getCliente());

        vendaService.cadastrarVenda(v);
        
        itemCarrinhoService.limparCarrinho(car);
        
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}")
    ResponseEntity removerVenda(@PathVariable Long id) {

        vendaService.excluirVenda(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    void editarVenda() {
        System.out.println("edita");
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Venda> mostraVenda(@PathVariable Long id) {

        Venda ven;
        try {
            ven = vendaService.buscaVenda(id);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(ven, HttpStatus.OK);
}
}
