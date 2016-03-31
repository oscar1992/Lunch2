/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.persistencia.admin;

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
@Table(name = "ADM_TPROD")
public class ProductoEntity implements Serializable{
    @Id
    @Column(name = "PROD_PROD")
    private Integer id;
    @Column(name = "PROD_NOMB")
    private String nombre;
    @Column(name = "PROD_IMAG")
    private byte[] imagen;
    @Column(name = "PROD_PREC")
    private String precio;
    
    @JoinColumn(name = "PROD_CATE")
    @ManyToOne
    private CategoriaEntity categoria;
    @JoinColumn(name = "PROD_MARC")
    @ManyToOne
    private MarcaEntity marca;
    @JoinColumn(name = "PROD_GRUP")
    @ManyToOne
    private GrupoAlimenticioEntity grupo;

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

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
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

    public GrupoAlimenticioEntity getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoAlimenticioEntity grupo) {
        this.grupo = grupo;
    }
}
