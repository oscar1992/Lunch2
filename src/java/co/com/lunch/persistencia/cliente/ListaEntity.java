/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.persistencia.cliente;

import co.com.lunch.persistencia.admin.ProductoEntity;
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
@Table(name = "CLI_TLIST")
public class ListaEntity implements Serializable{
    @Id
    @Column(name = "LIST_LIST")
    private Integer idLista;
    @JoinColumn(name = "LIST_PROD")
    @ManyToOne
    private ProductoEntity producto;
    @JoinColumn(name = "LIST_TIPO")
    @ManyToOne
    private TipoLoncheraEntity tipoLonchera;



    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public TipoLoncheraEntity getTipoLonchera() {
        return tipoLonchera;
    }

    public void setTipoLonchera(TipoLoncheraEntity tipoLonchera) {
        this.tipoLonchera = tipoLonchera;
    }

    public Integer getIdLista() {
        return idLista;
    }

    public void setIdLista(Integer idLista) {
        this.idLista = idLista;
    }

    
    
}
