/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.model.Carrinho;
import com.example.demo.model.Cliente;
import com.example.demo.repository.CarrinhoRepository;
import com.example.demo.repository.ClienteRepository;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jaqueline
 */
@Service
public class CarrinhoService {
    
    
    
   private final long TEMPOEXPIRACAO = TimeUnit.DAYS.toMillis(10);
    
     @Autowired
     CarrinhoRepository carrinhoRepository;
    public Carrinho buscaCarrinhoPorCliente(Cliente cli){
        
       Carrinho car = carrinhoRepository.findByCliente(cli);
       //se nao existe carrinho cria ele aqui
            if(car==null){
                car = new Carrinho();
                car.setCliente(cli);
             return   carrinhoRepository.save(car);
             //se ja existe retorna o carrinho existente
            }  else{
                //se o tempo do carrinho já expirou de acordo com a data atual
                if(car.getExpireTime().before(new Date(System.currentTimeMillis()))){
                    //limpa o carrinho 
                    //pegar o carrinho e limpa
                    //car. metodo que vai limpar ele
                    car.setItensCarrinho(null);
                    return car;
                }
                return car;
            }
            
    }
    
    public void cadastrarCarrinho(Carrinho c) {
        carrinhoRepository.save(c);
    }

    public void editarCarrinho(Carrinho c) {
        carrinhoRepository.save(c);
    }

   public void excluirCarrinho(Long id) {
        carrinhoRepository.deleteById(id);
    }
    
    
    
}
