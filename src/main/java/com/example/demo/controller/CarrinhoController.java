/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Carrinho;
import com.example.demo.model.ItemCarrinho;
import com.example.demo.model.Produto;
import com.example.demo.services.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/auth/carrinho")
public class CarrinhoController {
    
     @Autowired
     CarrinhoService carrinhoService;
    
      @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
            //passa o id e a quantidade no body
            // e o header com o token que contem o id do carrinho do cliente
         ResponseEntity adicionarProdutoNoCarrinho(@RequestBody Produto prod,@RequestHeader HttpHeaders headers) {
        
          //cria o carrinho passando o id do carrinho como parametro
          
          Carrinho carrinho = new Carrinho();
          
          carrinho.setId(retornaIdCarrinhoPeloToken(headers));
          
        ItemCarrinho itemCarrinho = new ItemCarrinho();
        
        itemCarrinho.setProduto(prod);
        itemCarrinho.setId(prod.getId());
        itemCarrinho.setQuantidade(prod.getQuantidade());
        
        
        //colocar todos os atributos que tem em item carrinho 
        //quantidade ta dentro de produto
        //set o produto pra denttro de item carrinho 
        // salvar o itemcarrinho 
        
        
        
        //criar metodo salvar no itemcarrinho service
        carrinhoService.cadastrarCarrinho(carrinho);

        return new ResponseEntity(HttpStatus.CREATED);

    }
    
    
    private long retornaIdCarrinhoPeloToken(HttpHeaders headers){
        
        //converter em long
         String token=headers.get("Authorization").get(0);
         long Token = Long.parseLong(token);
         return Token;
         
   
         
         
    }
}
