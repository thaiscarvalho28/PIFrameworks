/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.model.Categoria;
import com.example.demo.model.Produto;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jaqueline
 */
@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;

    public void cadastrarProduto(Produto prod) {
        produtoRepository.save(prod);
    }

    public void editarProduto(Produto prod) {
        produtoRepository.save(prod);
    }


    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public Produto buscaProduto(Long id) {
        return produtoRepository.findById(id).get();
    }
    
    public List<Produto> mostraTodos() {
        return produtoRepository.findAll();
    }
}
