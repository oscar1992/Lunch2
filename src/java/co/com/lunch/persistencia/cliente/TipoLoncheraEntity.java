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
@Table(name = "CLI_TTIPO")
public class TipoLoncheraEntity implements Serializable{
    @Id
    @Column(name = "TIPO_TIPO")
    private Integer idTipoLonchera;
    @Column(name = "TIPO_NOMB")
    private String nombreTipo;
    @Column(name = "TIPO_CANT")
    private Integer cantidad;
    @JoinColumn(name = "TIPO_PEDI")
    @ManyToOne
    private PedidoEntity pedido;

    public Integer getIdTipoLonchera() {
        return idTipoLonchera;
    }

    public void setIdTipoLonchera(Integer idTipoLonchera) {
        this.idTipoLonchera = idTipoLonchera;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }
    
    
}
