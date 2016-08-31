/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.persistencia.cliente;

import co.com.lunch.persistencia.admin.ProductoEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author oscarramirez
 */
@Entity
@Table(name = "CLI_TFAVO")
public class FavoritoItemEntity implements Serializable{
    @Id
    @Column(name = "FAVO_FAVO")
    private Integer idFavorito;
    @JoinColumn(name = "FAVO_PROD")
    @ManyToOne
    private ProductoEntity producto;
    @JoinColumn(name = "FAVO_NLON")
    @ManyToOne
    private NumeroLoncheraEntity nlonchera;

    
    public Integer getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(Integer idFavorito) {
        this.idFavorito = idFavorito;
    }

    
    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public NumeroLoncheraEntity getNlonchera() {
        return nlonchera;
    }

    public void setNlonchera(NumeroLoncheraEntity nlonchera) {
        this.nlonchera = nlonchera;
    }
}
