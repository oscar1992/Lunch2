/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.persistencia.cliente;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@Table(name = "CLI_TPEDI")
public class PedidoEntity implements Serializable{
    @Id
    @Column(name = "PEDI_PEDI")
    private Integer idPedido;
    @JoinColumn(name = "PEDI_PADR")
    @ManyToOne
    private PadreEntity padre;
    @Column(name = "PEDI_FEPE")
    private Date fechaPedido;
    @Column(name = "PEDI_FEEN")
    private Date fechaEntrega;
    @Column(name = "PEDI_HORA")
    private String horaEntrega;
    @Column(name = "PEDI_VALO")
    private Double valor;
    @Column(name = "PEDI_CANT")
    private Integer cantidad;
    @Column(name = "PEDI_METO")
    private String metodo;
    @Column(name = "PEDI_ENTR")
    private boolean entregado;
    @Column(name = "PEDI_CANC")
    private boolean cancelado;

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public PadreEntity getPadre() {
        return padre;
    }

    public void setPadre(PadreEntity padre) {
        this.padre = padre;
    }

    public String getFechaPedido() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(fechaPedido);
    }

    public void setFechaPedido(String fechaPedido) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.fechaPedido = df.parse(fechaPedido);
        System.out.println("SETE: "+this.fechaPedido.toString());
    }

    public String getFechaEntrega() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(fechaEntrega);
    }

    public void setFechaEntrega(String fechaEntrega) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.fechaEntrega = df.parse(fechaEntrega);
        System.out.println("SETE: "+this.fechaEntrega.toString());
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }
    
    public Date getFechaEntregaDate(){
        return this.fechaEntrega;
    }
    
}
