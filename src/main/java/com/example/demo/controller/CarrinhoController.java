/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Produto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/carrinho")
public class CarrinhoController {
    
      @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
            //passa o id e a quantidade no body
            // e o header com o token que contem o id do carrinho do cliente
    ResponseEntity adicionarProdutoNoCarrinho(@RequestBody Produto prod,@RequestHeader HttpHeaders headers) {
        
          retornaIdCarrinhoPeloToken(headers);
        
        
        return new ResponseEntity(HttpStatus.CREATED);

    }
    
    
    private void retornaIdCarrinhoPeloToken(HttpHeaders headers){
        
         String token=headers.get("Authorization").get(0);
         
    }
}
