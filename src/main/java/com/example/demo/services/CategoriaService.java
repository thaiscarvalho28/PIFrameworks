/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.model.Categoria;
import com.example.demo.model.Cliente;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author Jaqueline
 */
@Service
public class CategoriaService {
    
    @Autowired
    CategoriaRepository categoriaRepository;

    public void cadastrarCategoria(Categoria cat) {
        categoriaRepository.save(cat);
    }

    public void editarCategoria(Categoria cat) {
        categoriaRepository.save(cat);
    }


    public void excluirCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    public Categoria buscaCategoria(Long id) {
        return categoriaRepository.findById(id).get();
    }
    
    public List<Categoria> mostraTodas() {
        return categoriaRepository.findAll();
    }
}
