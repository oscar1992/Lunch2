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
@Table(name = "ADM_TCOMB")
public class CombinacionesEntity implements Serializable{
    @Id
    @Column(name = "COMB_COMB")
    private Integer idCombinaciones;
    @Column(name = "COMB_NOMB")
    private String nombre;
    @Column(name = "COMB_ORDE")
    private Integer orden;
    @JoinColumn(name = "COMB_CAJA")
    @ManyToOne
    private CajaPredeterminadaEntity caja;

    public Integer getIdCombinaciones() {
        return idCombinaciones;
    }

    public void setIdCombinaciones(Integer idCombinaciones) {
        this.idCombinaciones = idCombinaciones;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public CajaPredeterminadaEntity getCaja() {
        return caja;
    }

    public void setCaja(CajaPredeterminadaEntity caja) {
        this.caja = caja;
    }
}
