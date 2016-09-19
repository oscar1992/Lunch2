/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.persistencia.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author oscarramirez
 */

@Entity
@Table(name = "ADM_TFENT")
public class FechaEntregaEntity implements Serializable{
    
    @Id
    @SequenceGenerator(name = "SECUID", sequenceName = "id_tfent", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SECUID")
    @Column(name = "FENT_FENT", unique = true, nullable = false)
    private Integer idFecha;
    @Column(name = "FENT_FECHA")
    private String fecha;
    @Column(name = "FENT_DIA")
    private Integer dia;

    public Integer getIdFecha() {
        return idFecha;
    }

    public void setIdFecha(Integer idFecha) {
        this.idFecha = idFecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }
    
    
    
}
