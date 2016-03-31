/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.persistencia.cliente;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name = "CLI_TDIA")
public class DiaEntity implements Serializable{
    @Id
    @Column(name = "DIA_DIA")
    private Integer id;
    @Column(name = "DIA_FECH")
    private Date fecha;
    @JoinColumn(name = "DIA_NINO")
    @ManyToOne
    private NinoEntity nino;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public NinoEntity getNino() {
        return nino;
    }

    public void setNino(NinoEntity nino) {
        this.nino = nino;
    }
    
    
}
