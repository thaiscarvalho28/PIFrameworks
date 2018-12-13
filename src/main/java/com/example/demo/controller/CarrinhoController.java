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
import com.example.demo.services.ItemCarrinhoService;
import com.example.demo.services.ProdutoService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
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

    @Autowired
    ItemCarrinhoService itemCarrinhoService;

    @Autowired
    ProdutoService produtoService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    //passa o id e a quantidade no body
    // e o header com o token que contem o id do carrinho do cliente
    ResponseEntity adicionarProdutoNoCarrinho(@RequestBody Produto prod, @RequestHeader HttpHeaders headers) {

        //cria o carrinho passando o id do carrinho como parametro
        Carrinho carrinho = new Carrinho();

        carrinho.setId(retornaIdCarrinhoPeloToken(headers));

        //produto que tem no estoque
        Produto produtoEstoque = produtoService.buscaProduto(prod.getId());

        ItemCarrinho iCarrinho = itemCarrinhoService.buscaProdutoNoCarrinho(carrinho, prod);

        if (iCarrinho == null) {
            // item nao foi add no carrinho ainda
            if (produtoEstoque.getQuantidade() >= prod.getQuantidade()) {
                //se a qtd no estoque for maior ou igual a quantidade inserida no carrinho
                //CRIA O ITEM NO CARRINHO
                ItemCarrinho itemCarrinho = new ItemCarrinho();
                //colocar todos os atributos que tem em item carrinho 
                //quantidade ta dentro de produto
                itemCarrinho.setProduto(prod);
                itemCarrinho.setQuantidade(prod.getQuantidade());
                itemCarrinho.setCarrinho(carrinho);

                itemCarrinhoService.salvaItemCarrinho(itemCarrinho);

                return new ResponseEntity(HttpStatus.CREATED);
            } else {
                return new ResponseEntity("A quantidade no estoque está indisponível!",HttpStatus.BAD_REQUEST);
            }
        }else{
            //ver se a quantidade nao ultrapassa a do estoque
            if(produtoEstoque.getQuantidade() >= (iCarrinho.getQuantidade()+prod.getQuantidade())){
                iCarrinho.setQuantidade(iCarrinho.getQuantidade()+prod.getQuantidade());
                itemCarrinhoService.salvaItemCarrinho(iCarrinho);
                return new ResponseEntity(HttpStatus.CREATED);
            }else{
                return new ResponseEntity("A quantidade no estoque está indisponível!",HttpStatus.BAD_REQUEST); 
            }
        }

    }

    private long retornaIdCarrinhoPeloToken(HttpHeaders headers) {

        //converter em long
        String tokenInteiro = headers.get("Authorization").get(0);
        String token = tokenInteiro.substring(7);

        long recebeIdCarrinho = 0;
        try {
            Claims claim;
            claim = Jwts.parser()
                    .setSigningKey(ClienteController.key)
                    .parseClaimsJws(token).getBody();

            recebeIdCarrinho = Long.parseLong("" + claim.get("idCarrinho"));

        } catch (JwtException e) {

        }
        return recebeIdCarrinho;
    }
}
