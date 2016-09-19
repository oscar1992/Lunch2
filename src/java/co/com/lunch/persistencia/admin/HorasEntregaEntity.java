/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.persistencia.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author oscarramirez
 */
@Entity
@Table(name = "ADM_TFHOR")
public class HorasEntregaEntity {
    @Id
    @SequenceGenerator(name = "SECUID", sequenceName = "id_tfhor", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SECUID")
    @Column(name = "FHOR_FHOR", unique = true, nullable = false)
    private Integer idHora;
    @Column(name = "FHOR_HORI")
    private String horaInicial;
    @Column(name = "FHOR_HORF")
    private String horaFinal;
    @ManyToOne
    @JoinColumn(name = "FHOR_FENT")
    private FechaEntregaEntity fecha;

    public Integer getIdHora() {
        return idHora;
    }

    public void setIdHora(Integer idHora) {
        this.idHora = idHora;
    }

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public FechaEntregaEntity getFecha() {
        return fecha;
    }

    public void setFecha(FechaEntregaEntity fecha) {
        this.fecha = fecha;
    }
    
    
}
