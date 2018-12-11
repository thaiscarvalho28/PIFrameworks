package com.example.demo.repository;

import com.example.demo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public Cliente findByEmailAndSenha(
            @Param("email") String email,
            @Param("senha") String senha
    );
    
    
}
