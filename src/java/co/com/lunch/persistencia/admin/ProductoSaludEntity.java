/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.persistencia.admin;

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
@Table(name = "ADM_TPRSA")
public class ProductoSaludEntity implements Serializable{
    @Id
    @Column(name = "PRSA_PRSA")
    private Integer idProductosalud;
    @JoinColumn(name = "PRSA_PROD")
    @ManyToOne
    private ProductoEntity producto;
    @JoinColumn(name = "PRSA_SALU")
    @ManyToOne
    private SaludEntity salud;

    public Integer getIdProductosalud() {
        return idProductosalud;
    }

    public void setIdProductosalud(Integer idProductosalud) {
        this.idProductosalud = idProductosalud;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public SaludEntity getSalud() {
        return salud;
    }

    public void setSalud(SaludEntity salud) {
        this.salud = salud;
    }
    
    
}
