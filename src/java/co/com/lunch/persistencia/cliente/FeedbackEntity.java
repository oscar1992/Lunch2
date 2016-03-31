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
@Table(name = "CLI_TFEED")
public class FeedbackEntity implements Serializable{
    @Id
    @Column(name = "FEED_FEED")
    private Integer id;
    @Column(name = "FEED_NIVE")
    private Integer nivel;
    @Column(name = "FEED_COMM")
    private String comentario;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FEED_FECH")
    private Date fecha;
    @JoinColumn(name = "FEED_PADR")
    @ManyToOne
    private PadreEntity padre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public PadreEntity getPadre() {
        return padre;
    }

    public void setPadre(PadreEntity padre) {
        this.padre = padre;
    }
}
