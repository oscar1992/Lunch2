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
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author oscarramirez
 */
@Entity
@Table(name = "CLI_TPADR")
public class PadreEntity implements Serializable{
    @Id
    @Column(name = "PADR_PADR")
    private Integer idPadre;
    @Column(name = "PADR_NOMB")
    private String nombre;
    @Column(name = "PADR_TELE")
    private String telefono;
    @Column(name = "PADR_DIRE")
    private String direccion;
    @Column(name = "PADR_EMAI")
    private String email;
    @Column(name = "PADR_CONT")
    private String contrasena;
    @Column(name = "PADR_PRIM")
    private boolean primeravez;
    @Column(name = "PADR_NCON")
    private String numeroconfirmacion;
    @Column(name = "PADR_GENE")
    private String genero;
    @Column(name = "PADR_FOTO")
    private byte[] imagen;
    @Column(name = "PADR_TERM")
    private boolean termino;
    @Column(name = "PADR_FETE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date terminoFecha;

    public Integer getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Integer idPadre) {
        this.idPadre = idPadre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isPrimeravez() {
        return primeravez;
    }

    public void setPrimeravez(boolean primeravez) {
        this.primeravez = primeravez;
    }

    public String getNumeroconfirmacion() {
        return numeroconfirmacion;
    }

    public void setNumeroconfirmacion(String numeroconfirmacion) {
        this.numeroconfirmacion = numeroconfirmacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
/*
    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
*/
    public boolean isTermino() {
        return termino;
    }

    public void setTermino(boolean termino) {
        this.termino = termino;
    }

    public String getTerminoFecha() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(terminoFecha);
    }

    public void setTerminoFecha(String terminoFecha) throws ParseException {
        //this.terminoFecha = java.util.Date.from(Instant.parse(terminoFecha));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.terminoFecha = df.parse(terminoFecha);
        System.out.println("SETE: "+this.terminoFecha.toString());
    }
    
    
}
