
package com.example.demo.model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Carrinho {
    private Long id;
    private List<ItemCarrinho> itensCarrinho;
    private Date expireTime;
    private Cliente cliente;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @OneToOne
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    @OneToMany (cascade = CascadeType.REMOVE, mappedBy = "carrinho")
    public List<ItemCarrinho> getItensCarrinho() {
        return itensCarrinho;
    }

    public void setItensCarrinho(List<ItemCarrinho> itensCarrinho) {
        this.itensCarrinho = itensCarrinho;
    }
    
     
    
}
