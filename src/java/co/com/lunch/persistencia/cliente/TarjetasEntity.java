/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.persistencia.cliente;

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
@Table(name = "CLI_TTARJ")
public class TarjetasEntity implements Serializable{
    @Id
    @Column(name = "TARJ_TARJ")
    private Integer id;
    @Column(name = "TARJ_NOMB")
    private String nombre;
    @Column(name = "TARJ_NUME")
    private String numero;
    @JoinColumn(name = "TARJ_PADR")
    @ManyToOne
    private PadreEntity padre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public PadreEntity getPadre() {
        return padre;
    }

    public void setPadre(PadreEntity padre) {
        this.padre = padre;
    }
}
