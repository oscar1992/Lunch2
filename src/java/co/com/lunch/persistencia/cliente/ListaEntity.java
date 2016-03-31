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
@Table(name = "CLI_TLIST")
public class ListaEntity implements Serializable{
    @Id
    @Column(name = "LIST_LIST")
    private Integer id;
    @JoinColumn(name = "LIST_PROD")
    @ManyToOne
    private ProductoEntity producto;
    @JoinColumn(name = "LIST_DIA")
    @ManyToOne
    private DiaEntity dia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public DiaEntity getDia() {
        return dia;
    }

    public void setDia(DiaEntity dia) {
        this.dia = dia;
    }
    
}
