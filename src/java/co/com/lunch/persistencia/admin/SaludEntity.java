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
@Table(name = "ADM_TSALU")
@Entity
public class SaludEntity implements Serializable{
    @Id
    @Column(name = "SALU_SALU")
    private Integer idSalud;
    @Column(name = "SALU_NOM")
    private String nombreSalud;

    public Integer getIdSalud() {
        return idSalud;
    }

    public void setIdSalud(Integer idSalud) {
        this.idSalud = idSalud;
    }

    public String getNombreSalud() {
        return nombreSalud;
    }

    public void setNombreSalud(String nombreSalud) {
        this.nombreSalud = nombreSalud;
    }
    
    
}
