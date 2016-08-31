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
@Table(name = "ADM_TTIPO")
public class TipoInformacionEntity implements Serializable {
    @Id
    @Column(name = "TIPO_TIPO")
    private int idTinfo;
    @Column(name = "TIPO_NOMB")
    private String tipoNomb;

    public int getIdTinfo() {
        return idTinfo;
    }

    public void setIdTinfo(int id) {
        this.idTinfo = id;
    }

    public String getTipoNombre() {
        return tipoNomb;
    }

    public void setTipoNombre(String tipo) {
        this.tipoNomb = tipo;
    }
    
    
}
