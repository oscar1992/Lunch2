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
@Table(name = "ADM_TITEM")
public class ItemEntity implements Serializable{
    @Id
    @Column(name = "ITEM_ITEM")
    private Integer idItem;
    @JoinColumn(name = "ITEM_PROD")
    @ManyToOne
    private ProductoEntity producto;
    @JoinColumn(name = "ITEM_COMB")
    @ManyToOne
    private CombinacionesEntity combinacion;

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public CombinacionesEntity getCombinacion() {
        return combinacion;
    }

    public void setCombinacion(CombinacionesEntity combinacion) {
        this.combinacion = combinacion;
    }
}
