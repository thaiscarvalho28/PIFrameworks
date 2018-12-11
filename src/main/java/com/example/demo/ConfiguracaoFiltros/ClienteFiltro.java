/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.ConfiguracaoFiltros;

import com.example.demo.controller.ClienteController;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.filter.GenericFilterBean;

/**
 *
 * @author Jaqueline
 */
public class ClienteFiltro extends GenericFilterBean{

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) 
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)sr;
        
        String header = request.getHeader("Authorization");
        
        if(header==null || !header.startsWith("Bearer")){
            throw new ServletException("Não foi possivel validar o token");
        }
        
        String token = header.substring(7);
        try{
            Jwts.parser()
                    .setSigningKey(ClienteController.key)
                    .parseClaimsJws(token);
        }catch(JwtException e){
            throw new ServletException(e);
        }
        fc.doFilter(sr, sr1);
    }
    
}
