/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.persistencia.cliente;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author oscarramirez
 */
@Entity
@Table(name = "CLI_TNINO")
public class NinoEntity implements Serializable{
    @Id
    @Column(name="NINO_NINO")
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "NINO_FECH")
    private Date fechaNacimineto;
    @Column(name = "NINO_GENE")
    private String genero;
    @JoinColumn(name = "NINO_PADR")
    @ManyToOne
    private PadreEntity padre;
    @Column(name = "NINO_NOMB")
    private String nombre;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaNacimineto() {
        return fechaNacimineto;
    }

    public void setFechaNacimineto(Date fechaNacimineto) {
        this.fechaNacimineto = fechaNacimineto;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public PadreEntity getPadre() {
        return padre;
    }

    public void setPadre(PadreEntity padre) {
        this.padre = padre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
