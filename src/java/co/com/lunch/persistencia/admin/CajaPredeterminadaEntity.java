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
import javax.persistence.Table;

/**
 *
 * @author oscarramirez
 */
@Entity
@Table(name = "ADM_TCAJA")
public class CajaPredeterminadaEntity implements Serializable{
    @Id
    @Column(name = "CAJA_CAJA")
    private Integer idCaja;
    @Column(name = "CAJA_NOMB")
    private String nombre;
    @Column(name = "CAJA_COLO")
    private String color;

    public Integer getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Integer idCaja) {
        this.idCaja = idCaja;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
