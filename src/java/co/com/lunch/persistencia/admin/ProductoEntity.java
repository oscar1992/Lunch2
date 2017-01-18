/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.persistencia.admin;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author oscarramirez
 */
@Entity
@Table(name = "ADM_TPROD")
public class ProductoEntity implements Serializable{
    @Id
    @Column(name = "PROD_PROD")
    private Integer idProducto;
    @Column(name = "PROD_NOMB")
    private String nombre;
    @Column(name = "PROD_IMAG")
    private String imagen;
    @Column(name = "PROD_PREC")
    private String precio;
    @JoinColumn(name = "PROD_CATE")
    @ManyToOne
    private CategoriaEntity categoria;
    @JoinColumn(name = "PROD_MARC")
    @ManyToOne
    private MarcaEntity marca;
    @Column(name = "PROD_NIMA")
    private String nombreImagen;
    @JoinColumn(name = "PROD_INFO")
    @ManyToOne
    private InformacionNutricionalEntity info;
    @Column(name = "PROD_TIPO")
    private int tipo;
    @Column(name = "PROD_SALU")
    private boolean salud;
    @Column(name = "PROD_DISP")
    private boolean disponible;
    @Column(name = "PROD_ULTM")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ultimaActualizacion;

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public MarcaEntity getMarca() {
        return marca;
    }

    public void setMarca(MarcaEntity marca) {
        this.marca = marca;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public InformacionNutricionalEntity getInfo() {
        return info;
    }

    public void setInfo(InformacionNutricionalEntity info) {
        this.info = info;
    }

    public boolean isSalud() {
        return salud;
    }

    public void setSalud(boolean salud) {
        this.salud = salud;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Date getUltimaActualizacion() {
        //DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //return df.format(ultimaActualizacion);
        return this.ultimaActualizacion;
    }

    public void setUltimaActualizacion(Date ultimaActualizacion){
        this.ultimaActualizacion = ultimaActualizacion;
    }
    
}
