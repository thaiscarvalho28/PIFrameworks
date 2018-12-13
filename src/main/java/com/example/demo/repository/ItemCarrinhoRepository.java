/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Carrinho;
import com.example.demo.model.ItemCarrinho;
import com.example.demo.model.Produto;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

/**
 *
 * @author Jaqueline
 */
public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long>{
    
    //excluir os itens do carrinho 
    @Modifying
    @Transactional
    void deleteByCarrinho(Carrinho carrinho);
    
    
    public ItemCarrinho findByCarrinhoAndProduto(Carrinho carrinho, Produto prod);
}
