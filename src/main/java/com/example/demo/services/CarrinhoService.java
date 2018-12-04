/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.model.Carrinho;
import com.example.demo.model.Cliente;
import com.example.demo.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jaqueline
 */
@Service
public class CarrinhoService {
    
     @Autowired
     CarrinhoRepository carrinhoRepository;
    public Carrinho buscaCarrinhoPorCliente(Cliente cli){
        
       Carrinho car = carrinhoRepository.findByCliente(cli);
       //se nao existe carrinho cria ele aqui
            if(car==null){
                car = new Carrinho();
                car.setCliente(cli);
             return   carrinhoRepository.save(car);
            }  else{
                return car;
            }  
        
    }
    
}
