/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.model.Imagem;
import com.example.demo.model.Produto;
import com.example.demo.repository.ImagemRepository;
import configuração.FileStorageProperties;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jaqueline
 */
@Service
public class ImagemService {
    //armazena endereço do diretório de uploads
    private final Path fileStorageLocation;
    
    @Autowired
    
    private ImagemRepository imagemRepository;
    
    ImagemService(FileStorageProperties fsp){
        this.fileStorageLocation = Paths.get(fsp.getUploadDir()).toAbsolutePath().normalize();
        
        try{
            Files.createDirectories(fileStorageLocation);
        }catch(IOException e){
            System.out.println("Não foi possivel criar o diretório raíz de uploads  "+e);
        }
    }
    
    private String generatePath(){
        String path;
        
        LocalDateTime dateTime;
        dateTime = LocalDateTime.now();
        path="/"+dateTime.getYear()
        +"/"+dateTime.getMonth()
        + "/"+dateTime.getDayOfMonth()+"/";
        return path;
    }
    
    private String generateUUIDandExtension(MultipartFile file) throws IOException{
        
        String fullname=file.getOriginalFilename();
        String ext ="";
        int i = fullname.lastIndexOf('.');
        if(i==-1){
            throw new IOException("Arquivo sem extensão válida");
        }
        if(i>=0){
            ext=fullname.substring(i);
        }
        return UUID.randomUUID()+ext;
    }
    
    public void storageImagem(MultipartFile file, Produto p)throws IOException{
        
        String relativePath = generatePath()+generateUUIDandExtension(file);
        Path absolutePath;
        absolutePath = Paths.get(
            fileStorageLocation.toString()
            +relativePath).toAbsolutePath().normalize();
        
        try{
            Files.createDirectories(absolutePath);
            Files.copy(file.getInputStream(), absolutePath, StandardCopyOption.REPLACE_EXISTING);
            Imagem img = new Imagem();
            img.setNome(relativePath);
            img.setProduto(p);
            
            imagemRepository.save(img);
            
        }catch (IOException e){
            System.out.println("Erro: "+e);
        }
    }
        public Resource carregaImagemId(Long id) throws FileNotFoundException{
            Imagem img = imagemRepository.findById(id).get();
            
            try{
                if(img==null){
                    throw new FileNotFoundException("File not found");
                }
                Path imgpath = Paths.get(
                    fileStorageLocation.toString()
                    +img.getNome()).toAbsolutePath().normalize();
                
                Path fullPath = this.fileStorageLocation.resolve(imgpath).normalize();
                
                Resource resource = new UrlResource(fullPath.toUri());
                
                if(resource.exists()){
                    return resource;
                }
            } catch (NoSuchElementException | MalformedURLException e){
                throw new FileNotFoundException("File not found");
            }
            throw new FileNotFoundException("File not found");
        }
}
