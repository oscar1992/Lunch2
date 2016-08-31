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
@Table(name = "ADM_TTAGS")
public class TagsEntity implements Serializable{
    @Id
    @Column(name = "TAGS_TAGS")
    private Integer idTag;
    @Column(name = "TAGS_NOMB")
    private String nombreTag;
    @JoinColumn(name = "TAGS_PROD")
    @ManyToOne
    private ProductoEntity producto;

    public Integer getIdTag() {
        return idTag;
    }

    public void setIdTag(Integer idTag) {
        this.idTag = idTag;
    }

    public String getNombreTag() {
        return nombreTag;
    }

    public void setNombreTag(String nombreTag) {
        this.nombreTag = nombreTag;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }
}
