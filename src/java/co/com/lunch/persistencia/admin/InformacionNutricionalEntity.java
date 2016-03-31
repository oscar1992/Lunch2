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
@Table(name = "ADM_TINFO") 
public class InformacionNutricionalEntity implements Serializable{
    
    @Id
    @Column(name = "INFO_INFO")
    private Integer id;
    @Column(name = "INFO_TIPO")
    private String tipo;
    @Column(name = "VALOR")
    private double valor;
    @JoinColumn(name = "INFO_PROD")
    @ManyToOne
    private ProductoEntity item;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public ProductoEntity getItem() {
        return item;
    }

    public void setItem(ProductoEntity item) {
        this.item = item;
    }

    
    
    
}
