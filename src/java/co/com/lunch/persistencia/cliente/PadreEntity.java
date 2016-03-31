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
import javax.persistence.Table;

/**
 *
 * @author oscarramirez
 */
@Entity
@Table(name = "CLI_TPADR")
public class PadreEntity implements Serializable{
    @Id
    @Column(name = "PADR_PADR")
    private Integer id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    
}
