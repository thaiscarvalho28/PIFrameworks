/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.model.Carrinho;
import com.example.demo.model.ItemCarrinho;
import com.example.demo.model.Produto;
import com.example.demo.repository.ItemCarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jaqueline
 */
@Service
public class ItemCarrinhoService {

    @Autowired
    ItemCarrinhoRepository itemCarrinhoRepository;

    // método que vai limpar o carrinho 
    public void limparCarrinho(Carrinho carrinho) {
        itemCarrinhoRepository.deleteByCarrinho(carrinho);
    }

    public ItemCarrinho buscaProdutoNoCarrinho(Carrinho carrinho, Produto prod) {
        return itemCarrinhoRepository.findByCarrinhoAndProduto(carrinho, prod);
    }

    public ItemCarrinho salvaItemCarrinho(ItemCarrinho itemCarrinho){
     return itemCarrinhoRepository.save(itemCarrinho);
    }
    
}
