/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.services.ImagemService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jaqueline
 */
@RestController
public class ImagemController {
        @Autowired
        ImagemService imagemService;
    
   @RequestMapping(value="/admin/addimgprod/{id}",method = RequestMethod.POST) 
   public ResponseEntity addImagens(
        @PathVariable Long id,
        @RequestPart(value="file", required=false) MultipartFile[] files
   ){
       Produto p = new Produto();
       p.setId(id);
       for(MultipartFile f : files){
           try{
               imagemService.storageImagem(f,p);
           }catch (IOException e){
               return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
           }
       }
       return new ResponseEntity(HttpStatus.CREATED);
   }
   
   
   
   }
