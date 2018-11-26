/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Categoria;
import com.example.demo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Jaqueline
 */
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
//    public Produto findByNome(
//            @Param("nome") String nome,
//            @Param("preco") Double preco,
//            @Param("custo") Double custo
//    );
}
