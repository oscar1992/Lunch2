/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.persistencia.cliente;

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
@Table(name = "CLI_TNLON")
public class NumeroLoncheraEntity implements Serializable{
    @Id
    @Column(name = "NLON_NLON")
    private Integer idNumeroLonchera;
    @Column(name = "NLON_NOMB")
    private String nombreNumero;
    @JoinColumn(name = "NLON_PADR")
    @ManyToOne
    private PadreEntity padre;

    public Integer getIdNumeroLonchera() {
        return idNumeroLonchera;
    }

    public void setIdNumeroLonchera(Integer idNumeroLonchera) {
        this.idNumeroLonchera = idNumeroLonchera;
    }

    public String getNombreNumero() {
        return nombreNumero;
    }

    public void setNombreNumero(String nombreNumero) {
        this.nombreNumero = nombreNumero;
    }
    
    public PadreEntity getPadre() {
        return padre;
    }

    public void setPadre(PadreEntity padre) {
        this.padre = padre;
    }
}
