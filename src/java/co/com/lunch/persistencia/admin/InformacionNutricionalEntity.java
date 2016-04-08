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
    @JoinColumn(name = "INFO_TIPO")
    @ManyToOne
    private TipoInformacionEntity tipo;
    @Column(name = "VALOR")
    private double valor;
    @JoinColumn(name = "INFO_PROD")
    @ManyToOne
    private ProductoEntity item;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    

    public TipoInformacionEntity getTipo() {
        return tipo;
    }

    public void setTipo(TipoInformacionEntity tipo) {
        this.tipo = tipo;
    }

    
    
    
}
