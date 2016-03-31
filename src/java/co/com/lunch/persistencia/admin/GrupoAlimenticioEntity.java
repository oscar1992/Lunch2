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
@Table(name = "ADM_TGRUP")
public class GrupoAlimenticioEntity implements Serializable{
    @Id
    @Column(name = "GRUP_GRUP")
    private Integer id;
    @Column(name = "GRUP_RANG")
    private String rango;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }
}
